package com.rigger.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.rigger.util.JwtToken;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("input TokenInterceptor.....");

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        if (null != token && !JwtToken.isExpire(token)) {
            Map<String, Claim> claims = JwtToken.verifyToken(token);
            String userId = request.getParameter("userId");

            if (userId.equals(claims.get("userId").asString())) {
                return true;
            }
            responseMessage(response, "用户id不匹配");
            return false;

        }
        responseMessage(response, "没有token，或过期");
        return false;
    }

    private void responseMessage(HttpServletResponse response, String message) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        ApiResult<Object> failed = ApiResult.failed(message);
        String json = JsonMapper.objectToJson(failed);
        out.print(json);
        out.flush();
        out.close();
    }
}
