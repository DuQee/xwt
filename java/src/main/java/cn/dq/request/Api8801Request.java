package cn.dq.request;

public class Api8801Request {
    //首次平台提供/合伙人/代理编码
    public  String serviceId;
    //注册手机号 手机号和邮箱号不能同时为空
    public  String phoneNumber;
    //注册邮箱号 手机号和邮箱号不能同时为空
    public  String email;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
