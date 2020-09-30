import java.util.Scanner;

public class Employee extends Person implements ServisesInterface {

    public static String Position;
    public static Integer AccessLevel;
    public static Employee employee;
    public static Scanner myObj = new Scanner(System.in);

    public Employee(String name, Integer age, Integer credit,String position, Integer accessLevel){
        super(name, age, credit);
        Position = position;
        AccessLevel = accessLevel;
        /*Print.printout(employee.Name);
        Print.printout(employee.Age.toString());
        Print.printout(employee.Credit.toString());
        Print.printout(employee.Position);
        Print.printout(employee.AccessLevel.toString());*/
        myObj = new Scanner(System.in);
        int i;
        do{
            Getcommands();
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
        employee = null;
    }



    private void Getcommands(){
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
        try{MySQLfunctions.ChangeEmployeeProfile(employee.Credit, employee.Credit + sum);}
        catch (Exception e){Print.printout(e.toString());}
        employee.Credit = employee.Credit + sum;
    }

    @Override
    public void TakeCredit() {
        Print.printout("How much?");
        Integer sum = myObj.nextInt();
        if (employee.Credit < sum){
            Print.printout("You don't have enough credit");
            return;
        }
        try{MySQLfunctions.ChangeEmployeeProfile(employee.Credit, employee.Credit - sum);}
        catch (Exception e){Print.printout(e.toString());}
        employee.Credit = employee.Credit - sum;
    }

    @Override
    public void ViewInfo() {
        Print.printout(employee.toString());
    }

    @Override
    public String toString(){
        return "Name: " + super.Name + "/nAge: " + super.Age + "/nPosition: " + Position + "/nAccessLevel: " + AccessLevel;
    }
}
