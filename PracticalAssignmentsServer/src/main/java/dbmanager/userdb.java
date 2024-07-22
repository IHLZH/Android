package dbmanager;

import entity.User;

import javax.xml.transform.Result;
import java.sql.*;

public class userdb {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean userExit(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pa_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8",
                    "root",
                    "123456");
            String sql = "SELECT account FROM tb_user " + "WHERE account = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            resultset = preparedStatement.executeQuery();
            return resultset.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(resultset != null){
                try {
                    resultset.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int insert(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pa_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8",
                    "root",
                    "123456");
            String sql = "INSERT INTO tb_user (account, passward, name, avater, address)" + "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassward());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getAvater());
            preparedStatement.setString(5, user.getAddress());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static User selectUser(String account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pa_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8",
                    "root",
                    "123456");
            String sql = "SELECT * FROM tb_user " + "WHERE account = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            resultset = preparedStatement.executeQuery();
            User user = null;
            if(resultset.next()){
                user = new User();
                user.setAccount(resultset.getString("account"));
                user.setPassward(resultset.getString("passward"));
                user.setAvater(resultset.getInt("avater"));
                user.setName(resultset.getString("name"));
                user.setAddress(resultset.getString("address"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(resultset != null){
                try {
                    resultset.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
