package SerializeFunc;

import objects.SerializableClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class serialize {

    public void serializeIn(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))){
            SerializableClient client = new SerializableClient("denis", 20, 0, "lock");
            oos.writeObject(client);
            System.out.println("1");
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }

    public void serializeOut(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))){
            SerializableClient client = (SerializableClient)ois.readObject();
            System.out.println(client.Name +" "+client.Credit);
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }

}
