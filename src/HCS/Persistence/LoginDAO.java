package HCS.Persistence;

import java.sql.*;

public class LoginDAO implements ManageLoginDAO
{
  private JDBCController jdbcController;
  private static LoginDAO instance;

  private LoginDAO()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    jdbcController = JDBCController.getInstance();

  }

  public static synchronized LoginDAO getInstance()
  {
    if (instance == null)
    {
      instance = new LoginDAO();
    }
    return instance;
  }

  @Override public String Login(String username, String password)
  {
    String role=null;
    System.out.println("SEP2");
    try(Connection connection = jdbcController.getConnection()) {
      System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("SELECT role FROM userlogin WHERE username = ? AND password = ?");
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
    return role;
  }
}
