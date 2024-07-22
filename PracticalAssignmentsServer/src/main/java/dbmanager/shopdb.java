package dbmanager;

import entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class shopdb {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Shop> getShop(){
        List<Shop> shopList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pa_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8",
                    "root",
                    "123456");
            String sql = "SELECT * FROM tb_shop";
            preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                Shop shop = new Shop();
                shop.setId(resultset.getInt("id"));
                shop.setIntroduce(resultset.getString("introduce"));
                shop.setName(resultset.getString("name"));
                shop.setPic(resultset.getInt("pic"));
                shop.setInventory(resultset.getInt("inventory"));
                shop.setPrice(resultset.getDouble("price"));
                shopList.add(shop);
            }
            return shopList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
