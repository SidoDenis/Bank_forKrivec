package services;

import database.MySQLfunctions;
import objects.Client;
import objects.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Services {
    public ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Employee> employees = new ArrayList<>();
    public Map<String, Integer> creditamount = new HashMap<>();
    public String usertype;
    public Print print = new Print();

    public void services(MySQLfunctions mySQLfunctions){
        Scanner myObj = new Scanner(System.in);
        int i;
        print.printout(creditamount.keySet() + " " + creditamount.values());
        do{
            GetCommands();
            i = myObj.nextInt();
            switch (i){
                case 1:{
                    if(usertype.equals("client")){ clients.get(0).AddCredit( mySQLfunctions, this); }
                    else if(usertype.equals("employee")){ employees.get(0).AddCredit( mySQLfunctions ,this); }
                    break;
                }
                case 2:{
                    if(usertype.equals("client")){ clients.get(0).TakeCredit(mySQLfunctions, this); }
                    else if(usertype.equals("employee")){ employees.get(0).TakeCredit(mySQLfunctions,this); }
                    break;
                }
                case 3:{
                    if(usertype.equals("client")){ clients.get(0).ViewInfo(); }
                    else if(usertype.equals("employee")){ employees.get(0).ViewInfo(); }
                    break;
                }
            }
        } while (i!=0);
        employees.clear();
        clients.clear();
        usertype = "null";
    }

    private void GetCommands(){
        print.printout("Welcome:");
        print.printout("1. Put credit");
        print.printout("2. Take credit");
        print.printout("3. View information");
        print.printout("0. Log out");
    }

}
