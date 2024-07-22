package dbmanager;

import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class modifydb {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean modifyUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer result = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pa_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8",
                    "root",
                    "123456");
            //String sql = "update tb_user set passward=?,name=?,avater=?,address=? where account=?";
            String sql ="update tb_user set passward=?,name=?,avater=?,address=? where account=?";
            String name = user.getName();
            String pwd = user.getPassward();
            String account = user.getAccount();
            Integer avater = user.getAvater();
            String address = user.getAddress();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pwd);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, avater);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, account);

            result = preparedStatement.executeUpdate();
            return result != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
