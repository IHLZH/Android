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

@WebServlet("/login")
public class loginServlet extends HttpServlet {
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
        }else{
            User user = userdb.selectUser(account);
            if(user != null){
                if(pwd.equals(user.getPassward())){
                    printWriter.write(ResultUtils.success(200, "登录成功", user));
                }else{
                    printWriter.write(ResultUtils.error(500, "密码错误"));
                }
            }else{
                printWriter.write(ResultUtils.error(500, "用户不存在"));
            }
        }
        if(printWriter != null)printWriter.close();
    }
}
