package com.imoc.order.Utls;


import com.imoc.order.enums.codeEnum;

public class EnumUtils {
    public static  <T extends codeEnum> T getByCode(Integer code, Class<T> enmuClass){
        for(T each:enmuClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return  null;
    }
}
