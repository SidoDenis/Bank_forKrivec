package objects;


public abstract class Person {

    public  String Name;
    public  Integer Age;
    public  Integer Credit;
    public  String Password;

    public Person(String name, Integer age, Integer credit, String password){
        Name = name;
        Age = age;
        Credit = credit;
        Password = password;
    }

}
