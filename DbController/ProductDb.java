package DbController;

import java.sql.*;
import java.util.*;
import model.Product;

public class ProductDb{

    Connection getConnection() throws SQLException{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/c06","root","");
            return connection;
        }

     public boolean insertProduct(Product product) throws SQLException{         //insert new product

            Connection conn=getConnection();
            Statement statement=conn.createStatement();

            String query=String.format("insert into product values('%s','%s','%f','%d')",product.getProductId(),product.getProductName(),product.getPrice(),product.getAvailableQuantity());

            if(statement.executeUpdate(query)==1){
                return true;
            }else{
                return false;
            }
        }

      


     public Product getProduct(String id) throws SQLException{                  //search product
            Connection conn=getConnection();
            Statement statement=conn.createStatement();
            String query="select  *  from product where product_id='"+id+"'";
            ResultSet rs=statement.executeQuery(query);

            if(rs.next()){
                Product product = new Product(rs.getString("product_id"),rs.getString("product_name"),rs.getDouble("price"),rs.getInt("available_quantity"));
                return product;
            }else{
                return null;
            }

        }

    public Product getProductByName(String name)throws SQLException{
	
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from product where product_name = '"+name+"'";
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            Product product = new Product(rs.getString("product_id"),rs.getString("product_name"),rs.getDouble("price"),rs.getInt("available_quantity"));
            return product;
        }
        else{
                return null;
        }
	}

    public  boolean updateProduct (Product product) throws SQLException{        //update product info

         Connection conn=getConnection();
         Statement statement=conn.createStatement();
           String query=String.format("update product set product_name='%s',price=%f,available_quantity=%d where product_id='%s'",product.getProductName(),product.getPrice(),product.getAvailableQuantity(),product.getProductId());
           statement.executeUpdate(query);
           if(statement.executeUpdate(query)==1){
                return true;
            }else{
                return false;
            }
        }

       public  int deleteProduct(String id) throws SQLException{                //delete product
       Connection conn=getConnection();
       Statement statement=conn.createStatement();
       String query="delete from product where product_id='"+id+"'";
       int rs = statement.executeUpdate(query);
       if(rs>0){
           return 1;
       }
       else{
          return 0;
       }
   }
}