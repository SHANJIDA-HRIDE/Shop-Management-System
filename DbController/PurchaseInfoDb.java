package DbController;

import java.sql.*;
import model.*;

public class PurchaseInfoDb{
    Connection getConnection() throws SQLException{

       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/c06","root","");
       return connection;
    }
    public static double totalPrice =0;
    public int insertPurchase(PurchaseInfo purchaseInfo) throws SQLException{
        
       Connection conn=getConnection();
       Statement statement=conn.createStatement();
       
       Product product=new Product();
       ProductDb productDb=new ProductDb();
       product=productDb.getProduct(purchaseInfo.getProductId());
       
       if(product.getAvailableQuantity()>0){
        product.setAvailableQuantity(product.getAvailableQuantity()-purchaseInfo.getQuantity());
      }
      else{
        product.setAvailableQuantity(0);
      }
        
        productDb.updateProduct(product);
        
        totalPrice+=(product.getPrice() * purchaseInfo.getQuantity());
        purchaseInfo.setAmount(totalPrice);

       
       String query = String.format("insert into purchase_info (purchase_id,amount,purchase_date,user_id,product_id, quantity) values('%s','%f','%s','%s','%s','%d')",purchaseInfo.getPurchaseId(),purchaseInfo.getAmount(),purchaseInfo.getPurchaseDate(),purchaseInfo.getUserId(),purchaseInfo.getProductId(),purchaseInfo.getQuantity());
        
       if(statement.executeUpdate(query)==1){
            return 1;
        }else{
            return 0;
        }
       
    }
        
    public int  GetLastRow() throws SQLException{                          //get last row for count new line

        Connection conn=getConnection();
       Statement statement=conn.createStatement();

       String query = "select * from  purchase_info ORDER BY purchase_id DESC LIMIT 1";
       ResultSet rs = statement.executeQuery(query);

       if(rs.next()){

           return rs.getInt("purchase_id");
       }
        return -1;
    }
}
