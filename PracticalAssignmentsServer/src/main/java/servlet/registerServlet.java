package servlet;

import dbmanager.userdb;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ResultUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String account = req.getParameter("account");
        String pwd = req.getParameter("pwd");
        PrintWriter printWriter = resp.getWriter();
        if(account == null || "".equals(account)){
            printWriter.write(ResultUtils.error(500, "用户名不能为空"));
        }else if(pwd == null || "".equals(pwd)){
            printWriter.write(ResultUtils.error(500, "密码不能为空"));
        }else if(!userdb.userExit(account)){
            User user = new User();
            user.setPassward(pwd);
            user.setAccount(account);
            user.setAvater(2131165422);
            user.setAddress("未编辑");
            user.setName("未编辑");
            userdb.insert(user);
            printWriter.write(ResultUtils.success(200, "注册成功", null));
        }else{
            printWriter.write(ResultUtils.error(500, "用户名已存在"));
        }
        if(printWriter != null)printWriter.close();
    }
}
