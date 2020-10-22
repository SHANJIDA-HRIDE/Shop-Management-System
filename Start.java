import java.io.*;
import java.sql.*;

import DbController.*;
import model.*;
import gui.*;

public class Start {
    public static void main(String[] args) throws IOException, SQLException {
        AccountPage accountPage = new AccountPage();
		accountPage.setVisible(true);
    }
}