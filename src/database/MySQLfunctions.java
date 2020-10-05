package database;

import objects.Client;
import objects.Employee;
import services.Print;
import services.Services;

import java.sql.*;

public class MySQLfunctions {
    private String url = "jdbc:mysql://localhost:3306/People";
    private String user = "root";
    private String password = "33gjgeufq";
    public Print print = new Print();

    public  Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        }
        catch(Exception e){System.out.println(e);}
        return null;
    }

    public  void InsertClient(String name, Integer age, String password,Integer salary, Boolean isPremium, Integer credit) throws SQLException {
        Connection con = getConnection();
        String type = "client";
        PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Salary, IsPremium, Type, Credit)" +
                "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ salary +"', '"+ isPremium +"', '"+ type +"', '"+ credit +"')");
        line.executeUpdate();
        con.close();
    }

    public  void InsertEmployee(String name, Integer age, String password, String position, Integer accesslevel, Integer credit) throws SQLException{
        Connection con = getConnection();
        String type = "employee";
        PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Position, AccessLevel, Type, Credit)" +
                "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ position +"', '"+ accesslevel +"', '"+ type +"', '"+ credit +"')");
        line.executeUpdate();
        con.close();
    }

    public Services LogPerson(String password)throws SQLException {
        Connection con = getConnection();
        Integer num = 0;
        Services services = new Services();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM persons");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String Password = rs.getString("Password");
            String type = rs.getString("Type");
            if(Password.equals(password)){
                if(type.equals("client")){
                    String name = rs.getString("Name");
                    Integer age = rs.getInt("Age");
                    Integer salary = rs.getInt("Salary");
                    Boolean isPremium = Boolean.parseBoolean(rs.getString("IsPremium"));
                    Integer credit = rs.getInt("Credit");
                    con.close();
                    services.usertype = "client";
                    services.clients.add(new Client(name, age, credit, Password,salary, isPremium));
                    services.creditamount.put(name, credit);
                    return services;
                }
                else if(type.equals("employee")){
                    String name = rs.getString("Name");
                    Integer age = rs.getInt("Age");
                    String position = rs.getString("Position");
                    Integer accessLevel = rs.getInt("AccessLevel");
                    Integer credit = rs.getInt("Credit");
                    con.close();
                    services.usertype = "employee";
                    services.employees.add(new Employee(name, age, credit, Password,position, accessLevel));
                    services.creditamount.put(name, credit);
                    return services;
                }
                else continue;
            }

        }
        print.printout("Wrong password");
        return null;
    }

    public void ChangeEmployeeProfile(Integer prevsum, Integer currSum, Services services) throws SQLException{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM persons");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String password = rs.getString("Password");
            String name = rs.getString("Name");
            Integer age = rs.getInt("Age");
            String position = rs.getString("Position");
            Integer accessLevel = rs.getInt("AccessLevel");
            Integer credit = rs.getInt("Credit");
            if(name.equals(services.employees.get(0).Name)&&age.equals(services.employees.get(0).Age)
                    &&position.equals(services.employees.get(0).Position)&&accessLevel.equals(services.employees.get(0).AccessLevel)
                    &&credit.equals(prevsum)){
                PreparedStatement deletestat = con.prepareStatement("DELETE FROM persons WHERE Password = '"+ password+"'");
                deletestat.executeUpdate();
                String type = "employee";
                PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Position, AccessLevel, Type, Credit)" +
                        "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ position +"', '"+ accessLevel +"', '"+ type +"', '"+ currSum +"')");
                line.executeUpdate();
            }

        }
        con.close();
    }

    public void ChangeClientProfile(Integer prevsum, Integer currSum,  Services services) throws SQLException{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM persons");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String password = rs.getString("Password");
            String name = rs.getString("Name");
            Integer age = rs.getInt("Age");
            Integer salary = rs.getInt("Salary");
            Boolean isPremium = Boolean.parseBoolean(rs.getString("IsPremium"));
            Integer credit = (rs.getInt("Credit"));
            if(name.equals(services.clients.get(0).Name)&&age.equals(services.clients.get(0).Age)
                    &&salary.equals(services.clients.get(0).Salary)&&isPremium.equals(services.clients.get(0).IsPremium)
                    &&credit.equals(prevsum)){
                PreparedStatement deletestat = con.prepareStatement("DELETE FROM persons WHERE Password = '"+ password +"'");
                deletestat.executeUpdate();
                String type = "client";
                PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Salary, IsPremium, Type, Credit)" +
                        "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ salary +"', '"+ isPremium +"', '"+ type +"', '"+ currSum +"')");
                line.executeUpdate();
                break;
            }
        }

        con.close();
    }

}
