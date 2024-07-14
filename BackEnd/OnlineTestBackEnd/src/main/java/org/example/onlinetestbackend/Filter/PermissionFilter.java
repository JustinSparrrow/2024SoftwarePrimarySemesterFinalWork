package org.example.onlinetestbackend.Filter;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.onlinetestbackend.Utils.JWTUtils;
import org.example.onlinetestbackend.pojo.Result;

import java.io.IOException;

@WebFilter(urlPatterns = {"/User/*","/Question/*"},filterName = "TestAdminFilter")
public class PermissionFilter implements Filter  {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURL().toString();
        String jwt = request.getHeader("Authorization");
        Claims claims = JWTUtils.parseToken(jwt);
        try {
            if (    claims.get("admin", Integer.class)==1                //如果是管理员就直接放行
                    || url.contains("User/userUpdate") && request.getParameter("userid")==claims.get("userid",String.class))//更改自己的信息也放行
            {
                chain.doFilter(request, response);
                return;
            }
            Result result=new Result(0,"no permission");
            resp.getWriter().write(JSONObject.toJSONString(result));
        }catch (Exception e){                           //其余情况全部拦截
            Result result=new Result(0,"no permission");
            resp.getWriter().write(JSONObject.toJSONString(result));
        }
    }
}
