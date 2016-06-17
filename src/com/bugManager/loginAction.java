package com.bugManager;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.opensymphony.xwork2.Action.LOGIN;

public class loginAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{


    private String username, password, userId;
    private SessionMap<String, Object> session;

    public SessionMap<String, Object> getSession() {
        return session;
    }

    public void setSession(SessionMap<String, Object> session) {
        this.session = session;
    }

    public String execute() throws Exception{

        int status = loginDao.authenticate(this);
        if(status == 1){

            Cookie[] cookies = servletRequest.getCookies();
            for(int i=0; i< cookies.length; i++){
                Cookie c = cookies[i];
                if(c.getName().equals("userIdCookie") && !c.getValue().equals("")){
                    c.setValue(userId);
                    c.setMaxAge(60 * 60 * 24);
                    servletResponse.addCookie(c);
                }

                if(c.getName().equals("usernameCookie") && !c.getValue().equals("")){
                    c.setValue(username);
                    c.setMaxAge(60 * 60 * 24);
                    servletResponse.addCookie(c);
                }


            }

            System.out.println("login userId = " + userId);
            Cookie userIdCookie = new Cookie("userIdCookie", String.format("%s",userId));
            userIdCookie.setMaxAge(60 * 60 * 24);
            servletResponse.addCookie(userIdCookie);
            Cookie usernameCookie = new Cookie("usernameCookie", String.format("%s", username));
            usernameCookie.setMaxAge(60 * 60 * 24);
            servletResponse.addCookie(usernameCookie);
            System.out.println("cookies created");
            session.put("id", userId);
            return "success";
        }

        else {
            System.out.println("failure");
                return "error";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(Map<String, Object> map){
        session = (SessionMap<String, Object>)map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        servletRequest = httpServletRequest;
    }

    HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        servletResponse = httpServletResponse;
    }

}
