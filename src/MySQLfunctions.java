import java.sql.*;

public class MySQLfunctions {
    private static String url = "jdbc:mysql://localhost:3306/People";
    private static String user = "root";
    private static String password = "33gjgeufq";


    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        }
        catch(Exception e){System.out.println(e);}
        return null;
    }

    public static void InsertClient(String name, Integer age, String password,Integer salary, Boolean isPremium, Integer credit) throws SQLException {
        Connection con = getConnection();
        String type = "client";
        PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Salary, IsPremium, Type, Credit)" +
                "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ salary +"', '"+ isPremium +"', '"+ type +"', '"+ credit +"')");
        line.executeUpdate();
        con.close();
    }

    public static void InsertEmployee(String name, Integer age, String password, String position, Integer accesslevel, Integer credit) throws SQLException{
        Connection con = getConnection();
        String type = "employee";
        PreparedStatement line = con.prepareStatement("INSERT INTO persons (Name, Age, Password, Position, AccessLevel, Type, Credit)" +
                "VALUES ('" + name + "','" + age + "','"+ password +"', '"+ position +"', '"+ accesslevel +"', '"+ type +"', '"+ credit +"')");
        line.executeUpdate();
        con.close();
    }

    public static String LogPerson(String password)throws SQLException {
        Connection con = getConnection();
        Integer num = 0;
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
                    Print.printout("Hello, " + name);
                    Client.client = new Client(name, age, credit, salary, isPremium);
                    return ("Goodbye, " + name);
                }
                else if(type.equals("employee")){
                    String name = rs.getString("Name");
                    Integer age = rs.getInt("Age");
                    String position = rs.getString("Position");
                    Integer accessLevel = rs.getInt("AccessLevel");
                    Integer credit = rs.getInt("Credit");
                    Print.printout("Hello, " + name);
                    Employee.employee = new Employee(name, age, credit, position, accessLevel);
                    return ("Goodbye, " + name);
                }
                else continue;
            }

        }
        con.close();
        return ("Wrong password");
    }

    public static void ChangeEmployeeProfile(Integer prevsum, Integer currSum) throws SQLException{
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
            if(name.equals(Employee.employee.Name)&&age.equals(Employee.employee.Age)
                    &&position.equals(Employee.employee.Position)&&accessLevel.equals(Employee.employee.AccessLevel)
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

    public static void ChangeClientProfile(Integer prevsum, Integer currSum) throws SQLException{
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
            if(name.equals(Client.client.Name)&&age.equals(Client.client.Age)
                    &&salary.equals(Client.client.Salary)&&isPremium.equals(Client.client.IsPremium)
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
