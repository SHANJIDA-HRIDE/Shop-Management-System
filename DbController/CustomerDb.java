package DbController;

import java.sql.*;
import java.util.*;
import model.Customer;

public class CustomerDb{

    Connection getConnection() throws SQLException{

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/c06","root","");
        return connection;
    }

    public boolean insertCustomer(Customer customer) throws SQLException{ 
        Connection conn=getConnection();
        Statement statement=conn.createStatement();

        String query=String.format("insert into customer values('%s','%s','%s','%s')",customer.getUserId(),customer.getCustomerName(),customer.getPhoneNumber(),customer.getAddress());

        if(statement.executeUpdate(query)==1){
            return true;
        }else{
            return false;
        }


    }

        

    public Customer getCustomer(String id) throws SQLException{             //search customer
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from customer where user_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);

        if(rs.next()){
            Customer customer=new Customer(rs.getString("user_id"),rs.getString("customer_name"),rs.getString("phone_number"),rs.getString("address"));
            return customer;
        }else{
            return null;
        }

    }



    public boolean updateCustomer (Customer customer) throws SQLException{  //update customer

        Connection conn=getConnection();
        Statement statement=conn.createStatement();


        String query=String.format("update customer set customer_name='%s',phone_number='%s',address='%s' where user_id='%s'",customer.getCustomerName(),customer.getPhoneNumber(),customer.getAddress(),customer.getUserId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteCustomer(String id) throws SQLException{           //delete customer 
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from customer where user_id='"+id+"'";
        statement.executeUpdate(query);

        if(statement.executeUpdate(query)==1){
               return true;
       }
        else{
               return false;
       }
    }


}