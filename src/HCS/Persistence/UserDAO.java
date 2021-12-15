package HCS.Persistence;

import HCS.shared.transferObjects.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements ManageUserDAO
{
 private static UserDAO instance;
  private JDBCController jdbcController;
  private UserDAO()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    jdbcController = JDBCController.getInstance();
  }
  public static synchronized UserDAO getInstance()
  {
    if (instance == null)
    {
      instance = new UserDAO();
    }
    return instance;
  }




  @Override public boolean UserExist(String username)
  {
    boolean exist=false;
    //System.out.println("CREATEUSERDATABASE");
    try(Connection connection = jdbcController.getConnection()) {

     // System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("SELECT username FROM userlogin WHERE username = ?");
      statement.setString(1,username);
     // statement.setString(2,password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String a=resultSet.getString("username");
        System.out.println(a);
        if (a.equals(username))
        exist = true;
      }
     // System.out.println(role);
      // statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    //System.out.println(role);
    return exist;
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password,String role)
  {

    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO userlogin VALUES (?,?,?,?,?,?)");
      statement.setString(1,firstname);
      statement.setString(2,lastname);
     // Date d=(Date) birthday.ge
      statement.setDate(3,birthday);
      statement.setString(4,username);
      statement.setString(5,password);
      statement.setString(6,role);


      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<User> GetUsers()
  {

    ArrayList<User> users =new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM userlogin ORDER BY username ASC  ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String username = resultSet.getString("username");
        String password= resultSet.getString("password");
        String role=resultSet.getString("role");
        users.add(new User(firstname,lastname,birthday,username,password,role));


      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return users;
  }

  @Override public void RemoveUser(String username)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM userlogin where username=?  AND role != ?");
      statement.setString(1,username);
      statement.setString(2,"ADMIN");
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public void HCRUpdateRole(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {
    try(Connection connection = jdbcController.getConnection()) {
      System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("UPDATE userlogin SET firstname=?,lastname=?,birthday=?,username=?,password=?,role=role WHERE username = ? ");
      statement.setString(1,username);
      statement.setString(2,password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        role = resultSet.getString("role");
      }
      System.out.println(role);
      // statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public void updateUser(String username, User user)
  {
    try(Connection connection = jdbcController.getConnection()) {
     // System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("UPDATE userlogin SET firstname=?,lastname=?,birthday=?,username=?,password=?,role=? WHERE username = ? ");
      statement.setString(1,user.getFirstname());
      statement.setString(2,user.getLastname());
      statement.setDate(3,user.getBirthday());
      statement.setString(4,user.getUsername());
      statement.setString(5,user.getPassword());
      statement.setString(6,user.getRole());
      statement.setString(7,username);
      //ResultSet resultSet = statement.executeQuery();
      statement.executeUpdate();
      // statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

  }

  @Override public void deleteAllUsers()
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM userlogin ");
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }


}
