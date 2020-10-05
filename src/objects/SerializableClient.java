package objects;

import java.io.Serializable;

public class SerializableClient implements Serializable {

    public  String Name;
    public  Integer Age;
    public  Integer Credit;
    public  String Password;

    public SerializableClient(String name, Integer age, Integer credit, String password){
        Name = name;
        Age = age;
        Credit = credit;
        Password = password;
    }
}
