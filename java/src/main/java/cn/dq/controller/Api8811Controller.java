package cn.dq.controller;
import cn.dq.request.Api8801Request;
import cn.dq.request.Api8811Request;
import cn.dq.request.BaseReqeust;
import cn.dq.tools.DuQeeKey;
import cn.dq.utils.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@Component
@Slf4j
public class Api8811Controller {

    /*
     * 获取发票类目
     *
     * */
    @RequestMapping(value = "/api8811/excut")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Api8811Request request)  throws  Exception {
        log.info("进入 register 函数");
        //构造请求头报文
        BaseReqeust brt=NonceStrTool.Ceatbase("8811");
        //构造业务参数 8801传入0

        Object obj = JSONObject.toJSON(request);
        //setBizContent 内容加密
       // String pk=RsaUtil.getRSAPublicKeyAsNetFormat(DuQeeKey.publicKey.getBytes());

        String data= RsaUtil.encryptByPublicKey(obj.toString(),DuQeeKey.publicKey) ;
        brt.setBizContent(data);
        String tmp=JSONObject.toJSON(brt).toString();
        Map<String, String> pram= new HashMap<String, String>();
        Map jbt=JSONObject.parseObject(tmp,Map.class);

        //构建Map<String, String>
        for (Object map: jbt.entrySet()){
            String tempKey=((Map.Entry)map).getKey().toString();
            if(!tempKey.equals("sign"))
            {
                Object tempVal=((Map.Entry)map).getValue();
                String val=tempVal==null?"":tempVal.toString();
                pram.put(tempKey,val);
            }
        }

        //排序生成加密字符串
        String tempSingTxt= ParamUtil.mapSortedByKey(pram);

        //实际的编写过程中此处转换需要在8801中转换后保存数据库中
        String pk8=RsaConvert.formatPkcs1ToPkcs8(DuQeeKey.privateKey);
        //生成签文
        String signStr=RsaUtil.sign(tempSingTxt.getBytes(), Base64.getDecoder().decode(pk8),"RSA2");
        brt.setSign(signStr);
        String json=JSONObject.toJSON(brt).toString();

        log.info("输入8801reqest:"+json);
        String urlStr="http://lgapi.vev.cn:8002/v1/api/invoice/category";
        //请求开放API
        String tempstr = httpUtls.httpPost(urlStr,json);
        log.info("8811返回："+tempstr);
        Map<String, Object> map2 = JSONArray.parseObject(tempstr);
        if( map2.get("code").toString().equals("10000"))
        {
            //处理解密
            String str= RsaUtil.decryptByPrivateKey(map2.get("bizContent").toString(),pk8);
            Object map3 = JSONArray.parseArray(str);
            map2.replace("bizContent",map3);
        }


        return  map2;
    }


}
