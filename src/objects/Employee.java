package objects;

import database.MySQLfunctions;
import services.Print;
import services.Services;
import services.ServisesInterface;

import java.util.Scanner;

public class Employee extends Person implements ServisesInterface {

    public  String Position;
    public  Integer AccessLevel;


    public Scanner myObj = new Scanner(System.in);
    public Print print = new Print();


    public Employee(String name, Integer age, Integer credit, String password,String position, Integer accessLevel){
        super(name, age, credit, password);
        Position = position;
        AccessLevel = accessLevel;

    }


    @Override
    public void AddCredit(MySQLfunctions mySQLfunctions, Services services) {
        print.printout("How much?");
        Integer sum = myObj.nextInt();
        try{
            mySQLfunctions.ChangeEmployeeProfile(this.Credit, this.Credit + sum, services);}
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
        try{
            mySQLfunctions.ChangeEmployeeProfile(this.Credit, this.Credit - sum, services);}
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
        return "Name: " + super.Name + "/nAge: " + super.Age + "/nPosition: " + Position + "/nAccessLevel: " + AccessLevel;
    }
}
