package com.rigger.controller;

import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统通用异常处理
 */
@RestController
public class ErrorController extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";
    private Log log = LogFactory.getLog(ErrorController.class);

    @Autowired
    private ObjectMapper objectMapper;

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = Collections.unmodifiableMap(super.getErrorAttributes(
                request, false));
        Throwable cause = getCause(request);
        int status = (Integer) model.get("status");
        //错误信息
        String message = (String) model.get("message");
        //友好提示
        String errorMessage = getErrorMessage(cause);
        String requestPath = (String) model.get("path");

        //后台打印日志信息方方便查错
        log.info(status + ":" + message, cause);
        log.info("requestPath" + ":" + requestPath);

        //后台打印日志信息方方便查错
        log.info(message, cause);
        response.setStatus(status);
        if (!isJsonRequest(request)) {
            ModelAndView view = new ModelAndView("/error.btl");
            view.addAllObjects(model);
            view.addObject("status", status);
            view.addObject("errorMessage", errorMessage);
            view.addObject("cause", cause);
            return view;

        } else {
            Map error = new HashMap();
            error.put("success", false);
            error.put("errorMessage", getErrorMessage(cause));
            error.put("message", message);
            ApiResult<Map> result = ApiResult.restResult(error, ApiErrorCode.FAILED);
            writeJson(response, result );
            return null;
        }


    }

    protected boolean isJsonRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        if (requestUri.endsWith(".json")) {
            return true;
        } else {
            return (request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null
                    && request.getHeader("X-Requested-With").contains("XMLHttpRequest")));
        }
    }

    protected void writeJson(HttpServletResponse response, ApiResult<Map> error) {
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(error));
        } catch (IOException e) {
            // ignore
        }
    }

    protected String getErrorMessage(Throwable ex) {
        /*不给前端显示详细错误*/
        return "服务器错误,请联系管理员";
    }

    protected Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }
        }
        return error;
    }


}
