import java.util.Scanner;

public class Entry {

    public static void main(String[] args) throws Exception{
        Scanner myObj = new Scanner(System.in);
        int i;
        do{
            Getcommands();
            i = myObj.nextInt();
            switch (i){
                case 1:{
                    SignIn.signin();
                    break;
                }
                case 2:{
                    CreateAccount.CreateData();
                    break;
                }
            }
        } while (i!=0);
    }

    public static void Getcommands(){
        Print.printout("Choose what to do:");
        Print.printout("1. Enter account");
        Print.printout("2. Create account");
        Print.printout("0. Exit");
    }
}
