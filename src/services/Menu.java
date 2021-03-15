package services;

import SerializeFunc.serialize;

import java.util.Scanner;

public class Menu {

    public SignIn signIn = new SignIn();
    public CreateAccount createAccount = new CreateAccount();
    public Print print = new Print();
    public serialize serialize = new serialize();

    public Menu(){ }

    public void showmenu() throws Exception{
        Scanner myObj = new Scanner(System.in);
        int i;
        do{
            Getcommands();
            i = myObj.nextInt();
            switch (i){
                case 1:{
                    signIn.signin();
                    break;
                }
                case 2:{
                    createAccount.CreateData();
                    break;
                }
            }
        } while (i!=0);
    }

    public void Getcommands(){
        print.printout("Choose what to do:");
        print.printout("1. Enter account");
        print.printout("2. Create account");
        print.printout("0. Exit");
    }
}
