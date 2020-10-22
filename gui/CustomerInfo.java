package gui;

import java.awt.*;
import javax.swing.*;

public class CustomerInfo extends JFrame{

    public CustomerInfo(){                                                      //see customer info

        super("Info");
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D5D8DC"));

        JLabel label = new JLabel("<html>Name: "+CustomerPage.customer.getCustomerName()
                                                    +"<br/>Address: "+CustomerPage.customer.getAddress()
                                                       +"<br/>Phone No: "+CustomerPage.customer.getPhoneNumber()
                                                           +"</html>",SwingConstants.CENTER);
        label.setBounds(50,0, 300,290);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);


        this.add(panel);
    }

}