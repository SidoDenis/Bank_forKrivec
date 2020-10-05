package objects;

import database.MySQLfunctions;
import services.Print;
import services.Services;
import services.ServisesInterface;

import java.util.Scanner;

public class Client extends Person implements ServisesInterface {

    public Integer Salary;
    public Boolean IsPremium;


    public Scanner myObj = new Scanner(System.in);
    public Print print = new Print();


    public Client(String name, Integer age,Integer credit,String password,Integer salary, Boolean isPremium){
        super(name, age, credit, password);
        Salary = salary;
        IsPremium = isPremium;

    }


    @Override
    public void AddCredit(MySQLfunctions mySQLfunctions, Services services) {
        print.printout("How much?");
        Integer sum = myObj.nextInt();
        Integer currSum = this.Credit + sum;
        try{
            mySQLfunctions.ChangeClientProfile(this.Credit, currSum,  services);}
        catch (Exception e){
            print.printout(e.toString());}
        this.Credit = this.Credit + sum;
    }

    @Override
    public void TakeCredit(MySQLfunctions mySQLfunctions, Services services) {
        print.printout("How much?");
        Integer sum = myObj.nextInt();
        if (this.Credit < sum){
            print.printout("You don't have enough credit");
            return;
        }
        Integer currSum = this.Credit - sum;
        try{
            mySQLfunctions.ChangeClientProfile(this.Credit, currSum, services);}
        catch (Exception e){
            print.printout(e.toString());}
        this.Credit = this.Credit - sum;
    }

    @Override
    public void ViewInfo() {
        print.printout(this.toString());
    }

    @Override
    public String toString(){
        String line;
        if(IsPremium) line = "yes";
        else line = "no";
        return "Name: " + this.Name + "/nAge: " + this.Age + "/nSalary: " + Salary + "/nIsPremium: " + line;
    }

}
