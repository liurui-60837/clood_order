package com.imoc.order.Utls;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：s时间加随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        System.currentTimeMillis();
        Integer num =  random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(num);
    }
}
