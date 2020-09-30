import java.util.Scanner;

public class SignIn {

    public static void signin() throws Exception{
        Scanner myObj = new Scanner(System.in);
        Print.printout("Enter password: ");
        String password = myObj.next();
        String line = MySQLfunctions.LogPerson(password);
        Print.printout(line);
}
}
