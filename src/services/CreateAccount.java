package services;

import database.MySQLfunctions;

import java.util.Scanner;

public class CreateAccount {

    public Print print = new Print();
    public MySQLfunctions mySQLfunctions = new MySQLfunctions();

    public  void CreateData() throws Exception{

        Scanner myObj = new Scanner(System.in);
        print.printout("Are you a client or the employee?");
        String Persontype = myObj.next();
        print.printout("What is your name?");
        String Name = myObj.next();
        print.printout("What is your age?");
        Integer Age = myObj.nextInt();
        print.printout("What is your password?");
        String Password = myObj.next();
        if(Persontype.equals("client")){
            print.printout("What is your salary?");
            Integer Salary = myObj.nextInt();
            print.printout("Are you a premium client?(yes/no)");
            Boolean IsPremium = null;
            String line = myObj.next();
            if(line.equals("yes")){IsPremium = true;}
            else if(line.equals("no")){IsPremium = false;}
            Integer credit = 0;
            mySQLfunctions.InsertClient(Name, Age, Password, Salary, IsPremium, credit);
        }
        if(Persontype.equals("employee")){
            print.printout("What is your position?");
            String Position = myObj.next();
            print.printout("What is your access level?");
            Integer AccessLevel = myObj.nextInt();
            Integer credit = 0;
            mySQLfunctions.InsertEmployee(Name, Age, Password,Position, AccessLevel, credit);
        }
    }

}
