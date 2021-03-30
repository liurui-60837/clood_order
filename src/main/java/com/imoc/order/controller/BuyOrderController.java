package com.imoc.order.controller;

import com.imoc.order.Utls.ResultVOUtil;
import com.imoc.order.VO.ResultVO;
import com.imoc.order.converter.OrderForm2OrderDTOConverter;
import com.imoc.order.dataobject.OrderDetail;
import com.imoc.order.dto.OrderDto;
import com.imoc.order.enums.ResultEnum;
import com.imoc.order.exception.SellException;
import com.imoc.order.form.OrderForm;
import com.imoc.order.respository.OrderDetailRepository;
import com.imoc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto creatResult =  orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();

        map.put("orderId",creatResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    @GetMapping("/find")
    public List<OrderDetail> find(){
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        return orderDetailList;
    }
}
