package cn.dq.request;

public class BaseReqeust {
    //商户编号（商户注册交易时固定88010018）（参与加签）
    public  String mchid;
    //接口版本号，1.0（参与加签）
    public  String version="1.0";
    //业务交易编号(接口文档提供业务) （参与加签）
    public  String tranCode;
    //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss" （参与加签）
    public  String timestamp;
    //生成的随机数(17位)，主要保证签名不可预测 （参与加签
    public  String nonceStr;
    //签名类型，目前支持RSA2（1024.PKCS#1）（参与加签）
    public  String signType;
    //请求码(调用者提供唯一标识) （参与加签）
    public  String tradeNo;
    //签名串（不参与加签）
    public  String sign;
    //使用Base64 编码加密以后的数据，图片部分字段需要urlencode（UTF-8）编码，详见各接口说明（参与加签）
    public  String bizContent;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }
}
