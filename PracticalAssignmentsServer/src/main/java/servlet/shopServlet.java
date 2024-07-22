package servlet;

import cn.hutool.json.JSONUtil;
import dbmanager.shopdb;
import entity.Shop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/shop")
public class shopServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        List<Shop> shopList = shopdb.getShop();
        String jsonStr = JSONUtil.toJsonStr(shopList);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(jsonStr);
    }
}
