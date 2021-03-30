package com.imoc.order.converter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imoc.order.dataobject.OrderDetail;
import com.imoc.order.dto.OrderDto;
import com.imoc.order.enums.ResultEnum;
import com.imoc.order.exception.SellException;
import com.imoc.order.form.OrderForm;


import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {
        public   static OrderDto convert(OrderForm orderForm){
            Gson gson = new Gson();
            OrderDto orderDto = new OrderDto();

            orderDto.setBuyerName(orderForm.getName());
            orderDto.setBuyerPhone(orderForm.getPhone());
            orderDto.setBuyerAddress(orderForm.getAddress());
            orderDto.setBuyerOpenid(orderForm.getOpenid());
            List<OrderDetail> orderDetailList = new ArrayList<>();
            try {
                orderDetailList =   gson.fromJson(
                        orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
                        }.getType()
                );
            } catch (Exception e){
                throw  new SellException(ResultEnum.PARAM_ERROR);
            }
            orderDto.setOrderDetailList(orderDetailList);
            return  orderDto;
        }

}
