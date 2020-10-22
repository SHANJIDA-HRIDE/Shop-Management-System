package gui;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import DbController.*;

public class DeleteProduct extends JFrame{

    private  JLabel userlabel;
    private JTextField userId;
    private JButton deletePro;
    public DeleteProduct(){

        super("Delete Product");
        this.setSize(500,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);

        userlabel = new JLabel("Enter product Id");
        userlabel.setBounds(80, 25,300, 30);
        userlabel.setBackground(Color.BLACK);
        userlabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(userlabel);

        userId = new JTextField();
        userId.setBounds(80,55,340, 30);
        userId.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(userId);

        deletePro= new JButton("CONFIRM");
         deletePro.setBackground(Color.decode("#566573"));
         deletePro.setForeground(Color.white);
         deletePro.setBounds(170,130,150,30);
         deletePro.setFont(new Font("Arial", Font.BOLD, 15));
         deletePro.setBorderPainted(false);
         deletePro.setFocusable(false);
         deletePro.addActionListener(e->deletePro());
        panel.add( deletePro);

        this.add(panel);
    }

    private void deletePro(){                                                   //delete product by employee and manager

        ProductDb productDb = new ProductDb();

        if(userId.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Insert Id");
         }
        else{
            try{
              int success = productDb.deleteProduct(userId.getText());
              if(success==1){
                  JOptionPane.showMessageDialog(null, "Product deleted");
                  userId.setText("");
              }
              else{
                  JOptionPane.showMessageDialog(null, "No match found");
              userId.setText("");
              }
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Please valid");
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Please valid");
            }
        }
    }

}