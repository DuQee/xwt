package cn.dq.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class ParamUtil {
    private static final String SIGN_PARAM_SEPARATOR = "&";

    /**
     * 解析queryString
     *
     * @param queryString
     *            请求参数字符串
     * @param enc
     *            编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> getParamsMap(String queryString, String enc) throws UnsupportedEncodingException {
        Map<String, String> paramsMap = new TreeMap<String, String>();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0, tmpIndex = 0;
            String subStr, param, value;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }

                tmpIndex = subStr.indexOf('=');
                param = subStr.substring(0, tmpIndex);
                value = subStr.substring(tmpIndex + 1);
                value = URLDecoder.decode(value, enc).replaceAll(" ", "+");

                if (!"".equals(param))
                    paramsMap.put(param, value);
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }

    /**
     * 组织签名需要的交易数据 不用排序
     *
     * @param map
     * @return
     */
    public static String getSignMsg(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        String key = "";
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            key = it.next();
            sb.append(key).append("=").append(map.get(key)).append(SIGN_PARAM_SEPARATOR);
        }
        if (sb.indexOf(SIGN_PARAM_SEPARATOR) > -1)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 组织签名需要的交易数据 排序 duqee Gavin
     *
     * @param param
     * @return
     */
    public static String mapSortedByKey(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>(param.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            if (i == keyList.size() - 1) {
                stringBuilder.append(key).append("=").append(param.getOrDefault(key, ""));
            } else {
                stringBuilder.append(key).append("=").append(param.getOrDefault(key, "")).append("&");
            }
        }
        return stringBuilder.toString();
    }

}
