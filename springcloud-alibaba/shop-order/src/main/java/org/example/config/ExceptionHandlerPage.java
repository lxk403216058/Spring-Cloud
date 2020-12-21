package org.example.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定义返回异常界面
@Component
@Slf4j
public class ExceptionHandlerPage implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        ResponseData responseData = null;
        if (e instanceof FlowException){
            log.info("接口被限流了");
            responseData = new ResponseData(-1, "接口被限流了");
        }else if (e instanceof DegradeException){
            log.info("接口降级了");
            responseData = new ResponseData(-2, "接口降级了");
        }else if (e instanceof ParamFlowException){
            log.info("参数限流了");
            responseData = new ResponseData(-3, "参数限流了");
        }else if (e instanceof AuthorityException){
            log.info("没有授权");
            responseData = new ResponseData(-4, "没有授权");
        }else if (e instanceof SystemBlockException){
            log.info("系统负载异常");
            responseData = new ResponseData(-5, "系统负载异常");
        }

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData{
    private int code;
    private String message;
}
