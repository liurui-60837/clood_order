package com.imoc.order.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.imoc.order.Utls.KeyUtil;
import com.imoc.order.dataobject.OrderDetail;
import com.imoc.order.dataobject.OrderMaster;
import com.imoc.order.dto.OrderDto;
import com.imoc.order.enums.OrderStatusEnum;
import com.imoc.order.enums.PaystatusEnum;
import com.imoc.order.enums.ResultEnum;
import com.imoc.order.exception.SellException;

import com.imoc.order.respository.OrderDetailRepository;
import com.imoc.order.respository.OrderMasterRepository;
import com.imoc.order.service.OrderService;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.CartDto;
import com.imooc.product.common.ProductInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.getUniqueKey();
        orderDto.setOrderId(orderId);
        //1,查询商品的、数量、价格、
        List<String> productIds = orderDto.getOrderDetailList().stream().map(e->e.getProductId()).collect(Collectors.toList());
        //调用商品接口查询商品
        List<ProductInfoDTO> productInfos = productClient.listForOrder(productIds);
        if(productInfos==null||productInfos.size()==0){
            throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //2.j计算总价
            BigDecimal orderAmout = new BigDecimal("0");
            for(OrderDetail orderDetai:orderDto.getOrderDetailList()){
                for(ProductInfoDTO productInfo:productInfos){
                    if(productInfo.getProductId().equals(orderDetai.getProductId())){
                        orderAmout = productInfo.getProductPrice()
                                .multiply(new BigDecimal(orderDetai.getProductQuantity()))
                                .add(orderAmout);
                        BeanUtils.copyProperties(productInfo,orderDetai);
                        orderDetai.setOrderId(orderId);
                        orderDetai.setDetailId(KeyUtil.getUniqueKey());
                        //订单详情入库
                        orderDetailRepository.save(orderDetai);
                    }
                }
            }

        //3.写入数据库（orderMater和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto ,orderMaster);
        orderMaster.setOrerId(orderId);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PaystatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDto> cartDtoList =
                orderDto.getOrderDetailList().stream().map(e->
                        new CartDto(e.getProductId(),e.getProductQuantity()))
                        .collect(Collectors.toList());
        //调用商品接口，扣库存
        productClient.deleteProductForOrder(cartDtoList);
        return orderDto;
    }

//    @Override
//    public OrderDto findOne(String orderId) {
//        Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
//        OrderMaster orderMaster = new OrderMaster();
//        if(optional!=null&&optional.isPresent()){
//             orderMaster = optional.get();
//        }
//        if(orderMaster == null){
//            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
//        if(CollectionUtils.isEmpty(orderDetailList)){
//            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId(orderMaster.getOrerId());
//        orderDto.setOrderDetailList(orderDetailList);
//        BeanUtils.copyProperties(orderMaster,orderDto);
//        return orderDto;
//    }
//
//    @Override
//    public Page<OrderDto> findList(String buyerOpendi, Pageable pageable) {
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpendi, pageable);
//        List<OrderDto> orderDtoList =  OrderMasterOrderDTOConverter.convert(orderMasterPage.getContent());
//        Page<OrderDto> orderDtoPage  = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
//        return orderDtoPage;
//    }
//
//    @Override
//    @Transactional
//    public OrderDto cancel(OrderDto orderDto) {
//        OrderMaster orderMaster = new OrderMaster();
//        //判定订单状态
//        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
//            System.out.println("取消订单,订单状态不正确");
//            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//        //修改订单状态
//        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
//        orderMaster.setOrerId(orderDto.getOrderId());
//        BeanUtils.copyProperties(orderDto,orderMaster);
//        OrderMaster updateResult =  orderMasterRepository.save(orderMaster);
//        if(updateResult ==null){
//            System.out.println("取消订单失败,更新订单失败");
//            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//        //f返回库存
//        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
//            System.out.println("取消订单，订单中无商品详情");
//            throw  new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
//        }
//        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
//                    .map(e->new CartDto(e.getProductId(),e.getProductQuantity()))
//                    .collect(Collectors.toList());
//        productService.increaseStock(cartDtoList);
//        //如果已经支付，需要退款
//        if(orderDto.getPayStatus().equals(PaystatusEnum.SUCCESS.getCode())){
//            //TODO
//        }
//
//        return orderDto;
//    }
//
//    @Override
//    @Transactional
//    public OrderDto finish(OrderDto orderDto) {
//        //判断订单状态
//        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//        //修改订单状态
//        orderDto.setOrderStatus(OrderStatusEnum.FINISHE.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        orderMaster.setOrerId(orderDto.getOrderId());
//        BeanUtils.copyProperties(orderDto,orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if(updateResult==null){
//            System.out.println("完结订单，更新失败");
//            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//
//        return orderDto;
//    }
//
//    @Override
//    @Transactional
//    public OrderDto paid(OrderDto orderDto) {
//        //判断订单状态
//        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//        //判断支付状态
//        if(!orderDto.getPayStatus().equals(PaystatusEnum.WAIT.getCode())){
//            System.out.println("订单支付状态不正确");
//            throw  new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
//        }
//        //修改支付状态
//        orderDto.setPayStatus(PaystatusEnum.SUCCESS.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        orderMaster.setOrerId(orderDto.getOrderId());
//        BeanUtils.copyProperties(orderDto,orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if(updateResult==null){
//            System.out.println("完结订单，更新失败");
//            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//        return orderDto;
//    }
//
//    @Override
//    public Page<OrderDto> findListAll(Pageable pageable) {
//        Page<OrderMaster> orderMasters =  orderMasterRepository.findAll(pageable);
//        List<OrderDto> orderDtoList =  OrderMasterOrderDTOConverter.convert(orderMasters.getContent());
//        Page<OrderDto> orderDtoPage  = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasters.getTotalElements());
//        return orderDtoPage;
//    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate("2020-19-20");
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
    public void test() {
// 写法1
        String fileName =  "F:\\Desktop\\数据迁移\\test.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 写法2
        fileName = "F:\\Desktop\\数据迁移\\test.xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "F:\\Desktop\\数据迁移\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read(fileName, DemoData.class, demoDataListener).sheet().doRead();

        List<DemoDataee> list = demoDataListener.getList();
        System.out.println(list);
       /* // 写法2：
        fileName = "F:\\Desktop\\数据迁移\\test.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }*/
    }
}
