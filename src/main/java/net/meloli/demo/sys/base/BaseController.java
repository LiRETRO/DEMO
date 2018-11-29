package net.meloli.demo.sys.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController extends BaseContext {
    // req res session
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /**
     * 请求会先进入标注ModelAttribute的方法，作为初始化
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        // 支持任何访问 - 2018-07-02 朱宇不要设置允许任何访问进入系统
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        String method= request.getMethod();
        if (method.equals("OPTIONS")){
            response.setStatus(200);
        }
    }
}
