package cn.itcast.oa.test.Calculator;

import org.junit.Test;

import java.sql.*;

public class testConnection {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/itcastoa?characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }


    @Test
    public void testConnection() throws Exception {
        String sql = "select * from itcast_user ";
        Connection connection = getConnection();
        //获取预处理statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置查询参数
        //preparedStatement.setInt(0 , 300);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString(2) + " : " + resultSet.getString(1));
        }

        closeResource(resultSet, preparedStatement, connection);
        System.out.println("资源已经关闭");

    }

    private void closeResource(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
