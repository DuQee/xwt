package cn.dq.utils;

import cn.dq.request.BaseReqeust;
import cn.dq.tools.DuQeeKey;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class NonceStrTool {
    //生成随机数
    public static String getNonceStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    //获取唯一编码
    public static String getuuid()
    {
        return  UUID.randomUUID().toString().replace("-", "");
    }

    //获取时间戳
    public  static  String gettimestamp(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    public  static BaseReqeust Ceatbase(String trandCode)
    {
        BaseReqeust brt=new BaseReqeust();
        //商户编号（商户注册交易时固定88010018）（参与加签）
        brt.setMchid(DuQeeKey.mchid);
        //业务交易编号(接口文档提供业务) （参与加签）
        brt.setTranCode(trandCode);
        brt.setTimestamp(NonceStrTool.gettimestamp());
        brt.setNonceStr(NonceStrTool.getNonceStr(17));
        brt.setSignType("RSA2");
        //请求码(调用者提供唯一标识) （参与加签）
        brt.setTradeNo(NonceStrTool.getuuid());
        return  brt;
    }


}
