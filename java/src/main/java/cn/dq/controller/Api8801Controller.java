package cn.dq.controller;

import cn.dq.request.Api8801Request;
import cn.dq.request.BaseReqeust;
import cn.dq.tools.DuQeeKey;
import cn.dq.utils.DqResult;
import cn.dq.utils.NonceStrTool;
import cn.dq.utils.RsaConvert;
import cn.dq.utils.httpUtls;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Component
@Slf4j
public class Api8801Controller {

    /*
    *商户信息注册换取商户编码和相关加密和解密信息
    *
    * */
    @RequestMapping(value = "/api8801/excut")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Api8801Request request)  throws  Exception {
        log.info("进入 register 函数");
        //构造请求头报文
        BaseReqeust brt=NonceStrTool.Ceatbase("8801");
        //商户编号（商户注册交易时固定88010018）（参与加签）
        brt.setMchid("88010018");
        Object  object = JSONObject.toJSON(request);
        //构造业务参数 8801传入0
        brt.setSign("0");
        brt.setBizContent(object.toString());
        String json=JSONObject.toJSON(brt).toString();
        log.info("输入8801reqest:"+json);
        String urlStr="http://lgapi.vev.cn:8002/v1/api/mer/register";
        //请求开放API
       String tempstr = httpUtls.httpPost(urlStr,json);
        log.info("8801返回："+tempstr);
        Map<String, Object> map2 = JSONArray.parseObject(tempstr);
        //map2.get("bizContent");

        //*************重点***************
        //实际的编写过程中此处转换需要保存数据中 此处的获取的privateKey 需要调用以下方法取得pk8
       // String pk8= RsaConvert.formatPkcs1ToPkcs8(DuQeeKey.privateKey);
        //****************************

        return  map2;
    }
}
