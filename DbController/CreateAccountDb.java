package DbController;

import java.sql.*;
import model.Login;

public class CreateAccountDb{

     Connection getConnection() throws SQLException{

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/c06","root","");
        return connection;
    }

    public void createAccountDB(Login login) throws SQLException{ 
        Connection connection = getConnection();

        String query = "insert into login (user_id, password,status) Values ('"+login.getUserId()+"','"+login.getPassword()+"','"+login.getStatus()+ "')";

        Statement statement =connection.createStatement();
        statement.executeUpdate(query);

        }

}