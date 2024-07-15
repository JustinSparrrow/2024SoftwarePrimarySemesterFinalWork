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

import static java.lang.Integer.parseInt;

@WebFilter(urlPatterns = {"/User/*","/Question/*"})     //不针对“/Test”，此类行为不需要管理员权限
public class PermissionFilter implements Filter  {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURL().toString();
        String jwt = request.getHeader("Authorization");
        Claims claims = JWTUtils.parseToken(jwt);

        try {
            if (    claims.get("admin", Integer.class)==1                //如果是管理员就直接放行
                    || url.contains("User/userUpdate") && parseInt(request.getParameter("userid"))==claims.get("userid",Integer.class)
                    && claims.get("admin",Integer.class)==null)//更改自己的信息也放行(但不能给自己改管理员权限)
            {
                chain.doFilter(request, response);
                return;
            }
            Result result=new Result(0,"no permission");
            resp.getWriter().write(JSONObject.toJSONString(result));
        }catch (Exception e){                           //其余情况全部拦截
            e.printStackTrace();
            Result result=new Result(0,"system wrong");
            resp.getWriter().write(JSONObject.toJSONString(result));
        }
    }
}
