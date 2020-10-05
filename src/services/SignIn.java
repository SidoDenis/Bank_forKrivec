package services;

import database.MySQLfunctions;
import java.util.Scanner;

public class SignIn {

    public MySQLfunctions mySQLfunctions = new MySQLfunctions();
    public Services services = new Services();

    public void signin() throws Exception{
        Scanner myObj = new Scanner(System.in);
        Print print = new Print();
        print.printout("Enter password: ");
        String password = myObj.next();
        services = mySQLfunctions.LogPerson(password);
        services.services(mySQLfunctions);
    }
}
