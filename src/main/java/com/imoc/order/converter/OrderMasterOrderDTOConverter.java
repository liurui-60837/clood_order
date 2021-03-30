package com.imoc.order.converter;


import com.imoc.order.dataobject.OrderMaster;
import com.imoc.order.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 */
public class OrderMasterOrderDTOConverter {

    public static OrderDto convert(OrderMaster orderMaster){

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderMaster.getOrerId());
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e->
                    convert(e)
                ).collect(Collectors.toList());
    }
}
