package com.bugManager;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by soorya on 01-Jun-16.
 */
public class logoutAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{

    private SessionMap<String, Object> session;

    public SessionMap<String, Object> getSession() {
        return session;
    }

    public void setSession(SessionMap<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception{
        Cookie[] cookies = httpServletRequest.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equals("userIdCookie") || c.getName().equals("usernameCookie")) {
                System.out.println("logging out: " + c.getName());
                c.setValue("");
                c.setMaxAge(0);
                httpServletResponse.addCookie(c);
                System.out.println(c.getValue());
            }
        }
        session.invalidate();

        return "success";
    }


    @Override
    public void setSession(Map<String, Object> map) {
        session = (SessionMap<String, Object>) map;
    }

    HttpServletRequest httpServletRequest;
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    HttpServletResponse httpServletResponse;
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }


}
