package org.example.onlinetestbackend.Filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinetestbackend.Utils.JWTUtils;
import org.example.onlinetestbackend.pojo.Result;

@WebFilter(urlPatterns = "/*")              //对于所有操作都需要登录校验
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        try {
            if (request.getRequestURI().toString().contains("login")) {     //如果是登录则直接放行
                chain.doFilter(request, resp);
                return;
            }

            String jwt = request.getHeader("Authorization");            //获取JWT令牌
            if(jwt != null) {                                      //校验JWT令牌，校验成功则放行
                try{
                    JWTUtils.parseToken(jwt);
                    chain.doFilter(request, resp);
                    return;
                }catch (Exception e){
                    e.printStackTrace();
                    Result result=new Result(0,"wrong jwt");
                    resp.getWriter().write(JSONObject.toJSONString(result));
                    return;
                }
            }
            Result result=new Result(0,"didnt login");
            resp.getWriter().write(JSONObject.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
