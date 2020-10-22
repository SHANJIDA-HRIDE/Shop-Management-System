package gui;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.time.*;
import javax.swing.*;
import model.*;
import DbController.*;
import static gui.AccountPage.customerId;

public class CustomerPage extends JFrame{

   private  JPanel panel;
   private  JButton logout,searchButton;
   private JTextField searchField, idField, quantityField;
   private JButton infoButton,deleteAccount,updateInfo,buyButton;
   public static JTable buytable;
   int i=0;

    public CustomerPage() throws IOException, SQLException{
        super("Customer");
        this.setSize(1080,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D5D8DC"));

        JLabel name =  new JLabel("Shop Management System");
        name.setBounds(30, 5,400, 55);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Serif", Font.TYPE1_FONT, 25));
        panel.add(name);


        logout = new JButton("Log Out");
        logout.setForeground(Color.BLACK);
        logout.setBounds(880,15,150,30);
        logout.addActionListener(e->logoutDone());
        panel.add(logout);

         searchField = new JTextField();
        searchField.setBounds(450,15, 150,30);
        searchField.setBackground(Color.white);
        searchField.setForeground(Color.black);
        searchField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(searchField);

		
        searchButton = new JButton("Search");
        searchButton.setBounds(600, 15, 100, 30);
        searchButton.addActionListener(e->{
            try{
                searchProduct();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        });
        panel.add(searchButton);
        
        

        

        infoButton = new JButton("Your Info");
        infoButton.setBounds(450, 60, 150, 50);
        infoButton.setForeground(Color.black);
        infoButton.setFocusable(false);
        infoButton.addActionListener(e->{
            try{
                custinfo();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        });
        panel.add(infoButton);

        updateInfo =  new JButton("Update Info");
        updateInfo.setBounds(450, 110, 150, 50);
        updateInfo.setForeground(Color.black);
        updateInfo.setFocusable(false);
        updateInfo.addActionListener(e->updateCustomer());
        panel.add(updateInfo);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBounds(450, 160, 150, 50);
        deleteAccount.setForeground(Color.black);
        deleteAccount.setFocusable(false);
        deleteAccount.addActionListener(e->{
            try{
                deleteAcc();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        });
        panel.add(deleteAccount);
        
        JLabel idLabel = new JLabel("Insert Id");
        idLabel.setBounds(350, 250, 100, 30);
        idLabel.setFont(new Font("Serif", Font.TYPE1_FONT, 15));
        panel.add(idLabel);
        
        idField = new JTextField();
        idField.setBounds(350,280, 150,30);
        idField.setBackground(Color.white);
        idField.setForeground(Color.black);
        idField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(idField);
        
        JLabel quantityLabel = new JLabel("Insert Quantity");
        quantityLabel.setBounds(550, 250, 150, 30);
        quantityLabel.setFont(new Font("Serif", Font.TYPE1_FONT, 15));
        panel.add(quantityLabel);
        
        quantityField = new JTextField();
        quantityField.setBounds(550,280, 150,30);
        quantityField.setBackground(Color.white);
        quantityField.setForeground(Color.black);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(quantityField);
        
        buyButton = new JButton("BUY");
        buyButton.setForeground(Color.BLACK);
        buyButton.setBounds(475,320,100,30);
        buyButton.addActionListener(e->{
            try {
                buyProduct();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        panel.add(buyButton);

        this.add(panel);

    }
    
    public static  boolean customerPage = false;
    public static Product product = null;
    public static Product productByName = null;
    
    public  void searchProduct() throws SQLException{                           //search product
        if(!"".equals(searchField.getText())){
            
            ProductDb productDb= new ProductDb();
            product = productDb.getProduct(searchField.getText());
            productByName = productDb.getProductByName(searchField.getText());
            if(product==null && productByName == null){
                JOptionPane.showMessageDialog(null, "Nothing found");
                return;
            }
            customerPage = true;
            ProductInfo productInfo = new ProductInfo();
            productInfo.setVisible(true);
            searchField.setText("");
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Enter product id");
         }
    }
  
   
    public void logoutDone(){                                                   //logout
        AccountPage accountPage =  new AccountPage();
        this.setVisible(false);
        accountPage.setVisible(true);
    }

    public static Customer customer=null;
    public void custinfo() throws SQLException{                                 //customer info
        CustomerDb customerDb =  new CustomerDb();
        customer=customerDb.getCustomer(customerId);
        CustomerInfo cuatomerInfo = new CustomerInfo();
        cuatomerInfo.setVisible(true);
    }

    public void deleteAcc() throws SQLException{                                 //delete customer account
                CustomerDb customerDb = new CustomerDb();
                LoginDb loginDb =  new LoginDb();
                customerDb.deleteCustomer(customerId);
                loginDb.deleteId(customerId);
                JOptionPane.showMessageDialog(null, "Your account has deleted");
                this.dispose();
    }

    private void updateCustomer(){                                              //update customer info
        UpdateInfoCustomer updateInfoCustomer = new UpdateInfoCustomer();
        updateInfoCustomer.setVisible(true);
    }

    private void buyProduct() throws SQLException {
        
        CustomerDb customerDb =  new CustomerDb();
        customer=customerDb.getCustomer(customerId);
        
        ProductDb productDb = new ProductDb();
        Product product = productDb.getProduct(idField.getText());
        if(product == null){
            JOptionPane.showMessageDialog(null, "No product matched");
            return;
        }
        else if(product.getAvailableQuantity()<Integer.parseInt(quantityField.getText())){    //chake available quantity
           JOptionPane.showMessageDialog(null, "Not enough quantity");
           idField.setText("");
           quantityField.setText("");
           return;
        }
        else{
            
            PurchaseInfoDb purchaseInfodb =  new PurchaseInfoDb();
            
            int lastId=purchaseInfodb.GetLastRow();
            System.out.println(lastId);
            double totalPrice = Integer.parseInt(quantityField.getText())*product.getPrice();
            
            PurchaseInfo purchaseInfo = new PurchaseInfo(lastId+1, customerId, totalPrice, LocalDate.now().toString(), idField.getText(), Integer.parseInt(quantityField.getText()));
            
            PurchaseInfoDb purchaseInfoDb  = new PurchaseInfoDb();
            int p=purchaseInfoDb.insertPurchase(purchaseInfo);
            if(p==1){
                JOptionPane.showMessageDialog(null,"Your cost : "+purchaseInfoDb.totalPrice);
            }
            else{
                System.out.println("Sorry");
            }
            idField.setText("");
            quantityField.setText("");
            
        }
   }

}