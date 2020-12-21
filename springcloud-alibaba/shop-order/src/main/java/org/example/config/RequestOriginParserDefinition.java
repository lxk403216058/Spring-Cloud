package org.example.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    //定义区分来源，本质作用是通过request域获取到来源标识
    //app pc
    //然后交给流控应用 位置进行匹配
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        StringBuffer url = request.getRequestURL();
        if (url.toString().endsWith("favicon.ico")) {
            // 浏览器会向后台请求favicon.ico图标
            return serviceName;
        }

        if (StringUtils.isEmpty(serviceName)) {
            throw new IllegalArgumentException("serviceName must not be null");
        }

        return serviceName;
    }
}
