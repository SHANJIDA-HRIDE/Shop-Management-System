package gui;

import java.awt.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import model.*;
import DbController.*;
import static gui.AccountPage.managerId;

public class ManagerPage extends JFrame {

   private  JPanel panel;
   private  JButton logout,searchButton,updateInfo,updateProduct;
   private JTextField searchField;
   private JButton infoButton,addProduct,addEmployee,deleteProduct,updateEmployee,deleteEmployee;

   public ManagerPage() throws IOException, SQLException{

        super("Manager");
        this.setSize(1080,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D5D8DC"));

        infoButton = new JButton("Your Info");
        infoButton.setBounds(450, 60, 150, 50);
        infoButton.setForeground(Color.black);
        infoButton.setFocusable(false);
        infoButton.addActionListener(e->{
            try{
                mnginfo();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        });
        panel.add(infoButton);

        updateInfo =  new JButton("Change Info");
        updateInfo.setBounds(450, 110, 150, 50);
        updateInfo.setForeground(Color.black);
        updateInfo.setFocusable(false);
        updateInfo.addActionListener(e->updateEmp());
        panel.add(updateInfo);

        addProduct =  new JButton("Add Product");
        addProduct.setBounds(450, 160, 150, 50);
        addProduct.setForeground(Color.black);
        addProduct.setFocusable(false);
        addProduct.addActionListener(e->addProduct());
        panel.add(addProduct);

        deleteProduct =  new JButton("Delete Product");
        deleteProduct.setBounds(450, 210, 150, 50);
        deleteProduct.setForeground(Color.black);
        deleteProduct.setFocusable(false);
        deleteProduct.addActionListener(e->deltProduct());
        panel.add(deleteProduct);

        updateProduct =  new JButton("Update Product");
        updateProduct.setBounds(450, 260, 150, 50);
        updateProduct.setForeground(Color.black);
        updateProduct.setFocusable(false);
        updateProduct.addActionListener(e->updProduct());
        panel.add(updateProduct);

         addEmployee =  new JButton("Add Employee");
         addEmployee.setBounds(450, 310, 150, 50);
        addEmployee.setForeground(Color.black);
        addEmployee.setFocusable(false);
        addEmployee.addActionListener(e->addEmp());
        panel.add(addEmployee);

        updateEmployee = new JButton("Update Employee");
        updateEmployee.setBounds(450,360,150,50);
        updateEmployee.setForeground(Color.black);
        updateEmployee.setFocusable(false);
        updateEmployee.addActionListener(e->updateEmp());
        panel.add(updateEmployee);

        deleteEmployee =  new JButton("Remove Employee");
        deleteEmployee.setBounds(450, 410, 150, 50);
        deleteEmployee.setForeground(Color.black);
        deleteEmployee.setFocusable(false);
        deleteEmployee.addActionListener(e->{
            try{
                deleteEmp();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        });
        panel.add(deleteEmployee);

        

        JLabel name =  new JLabel("Shop Management System");
        name.setBounds(30, 5,400, 55);
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Serif", Font.TYPE1_FONT, 25));
        panel.add(name);


        logout = new JButton("Log Out");
        logout.setForeground(Color.black);
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

        this.add(panel);

    }

    public void logoutDone(){                                                   //logout manager
        AccountPage accountPage =  new AccountPage();
        this.setVisible(false);
        accountPage.setVisible(true);
    }

    public static Employee employee=null;
    public  void mnginfo() throws SQLException{                                 //manager info
        EmployeeDb employeeDb =  new EmployeeDb();
        employee=employeeDb.getEmployee(managerId);
        ManagerInfo managerinfo = new ManagerInfo();
        managerinfo.setVisible(true);
    }

    public static  boolean managerPage = false;
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
            managerPage = true;
            ProductInfo productInfo = new ProductInfo();
            productInfo.setVisible(true);
            searchField.setText("");
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Enter product id");
         }
    }

    public void addEmp(){                                                       //add new employee

        AddEmployee addEmploye =  new AddEmployee();
        addEmploye.setVisible(true);
    }

    public void addProduct(){                                                   //add product

        AddProduct addProd = new AddProduct();
        addProd.setVisible(true);
    }

    public void deltProduct(){                                                  //delete product

        DeleteProduct deletePro = new DeleteProduct();
        deletePro.setVisible(true);
    }

    private void updateEmp(){                                                   //update all employee for manager
        UpdateInfoEmployee updateInfoemp = new UpdateInfoEmployee();
        updateInfoemp.setVisible(true);
    }

    private void updProduct(){                                                  //update product
        UpdateProduct updateProd = new UpdateProduct();
        updateProd.setVisible(true);
    }

    

    private void deleteEmp() throws SQLException{
            DeleteEmployee deleteEmployee = new DeleteEmployee();
            deleteEmployee.setVisible(true);
    }

}