import java.util.Scanner;

public class Client extends Person implements ServisesInterface {

    public static Integer Salary;
    public static Boolean IsPremium;
    public static Client client;
    public static Scanner myObj = new Scanner(System.in);

    public Client(String name, Integer age,Integer credit,Integer salary, Boolean isPremium){
        super(name, age, credit);
        Salary = salary;
        IsPremium = isPremium;
        /*Print.printout(client.Name);
        Print.printout(client.Age.toString());
        Print.printout(client.Credit.toString());
        Print.printout(client.Salary.toString());
        Print.printout(client.IsPremium.toString());*/
        Scanner myObj = new Scanner(System.in);
        int i;
        do{
            GetCommands();
            i = myObj.nextInt();
            switch (i){
                case 1:{
                    AddCredit();
                    break;
                }
                case 2:{
                    TakeCredit();
                    break;
                }
                case 3:{
                    ViewInfo();
                    break;
                }
            }
        } while (i!=0);
        client = null;
    }

    private void GetCommands(){
        Print.printout("Welcome:");
        Print.printout("1. Put credit");
        Print.printout("2. Take credit");
        Print.printout("3. View information");
        Print.printout("0. Log out");
    }

    @Override
    public void AddCredit() {
        Print.printout("How much?");
        Integer sum = myObj.nextInt();
        Integer currSum = client.Credit + sum;
        try{MySQLfunctions.ChangeClientProfile(client.Credit, currSum);}
        catch (Exception e){Print.printout(e.toString());}
        client.Credit = client.Credit + sum;
    }

    @Override
    public void TakeCredit() {
        Print.printout("How much?");
        Integer sum = myObj.nextInt();
        if (Client.Credit < sum){
            Print.printout("You don't have enough credit");
            return;
        }
        Integer currSum = client.Credit - sum;
        try{MySQLfunctions.ChangeClientProfile(client.Credit, currSum);}
        catch (Exception e){Print.printout(e.toString());}
        client.Credit = client.Credit - sum;
    }

    @Override
    public void ViewInfo() {
        Print.printout(client.toString());
    }

    @Override
    public String toString(){
        String line;
        if(IsPremium) line = "yes";
        else line = "no";
        return "Name: " + super.Name + "/nAge: " + super.Age + "/nSalary: " + Salary + "/nIsPremium: " + line;
    }

}
