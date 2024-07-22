package servlet;

import com.google.gson.Gson;
import dbmanager.modifydb;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/modify")
public class modifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        ServletInputStream inputStream = req.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String json = "";
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            json += line;
        }
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        PrintWriter printWriter = null;
        if(modifydb.modifyUser(user)){
            printWriter = resp.getWriter();
            printWriter.write("修改成功！");
        }
        if(printWriter != null)printWriter.close();
    }
}
