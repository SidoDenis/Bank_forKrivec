import java.util.Scanner;

public class CreateAccount {

    public static void CreateData() throws Exception{

        Scanner myObj = new Scanner(System.in);
        Print.printout("Are you a client or the employee?");
        String Persontype = myObj.next();
        Print.printout("What is your name?");
        String Name = myObj.next();
        Print.printout("What is your age?");
        Integer Age = myObj.nextInt();
        Print.printout("What is your password?");
        String Password = myObj.next();
        if(Persontype.equals("client")){
            Print.printout("What is your salary?");
            Integer Salary = myObj.nextInt();
            Print.printout("Are you a premium client?(yes/no)");
            Boolean IsPremium = null;
            String line = myObj.next();
            if(line.equals("yes")){IsPremium = true;}
            else if(line.equals("no")){IsPremium = false;}
            Integer credit = 0;
            MySQLfunctions.InsertClient(Name, Age, Password, Salary, IsPremium, credit);
        }
        if(Persontype.equals("employee")){
            Print.printout("What is your position?");
            String Position = myObj.next();
            Print.printout("What is your access level?");
            Integer AccessLevel = myObj.nextInt();
            Integer credit = 0;
            MySQLfunctions.InsertEmployee(Name, Age, Password,Position, AccessLevel, credit);
        }
    }

}
