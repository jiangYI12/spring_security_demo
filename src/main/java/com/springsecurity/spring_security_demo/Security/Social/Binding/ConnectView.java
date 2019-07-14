package com.springsecurity.spring_security_demo.Security.Social.Binding;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//返回页面
//绑定成功页面 配置在SocialConfig中
public class ConnectView extends AbstractView {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
     * (java.util.Map, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connection") == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>绑定成功</h3>");
        }

    }

}

