package HCS.DataBase;

import HCS.shared.transferObjects.Role;

import java.sql.*;
import java.util.ArrayList;

public class ReceptionDAO implements ManageReceptionDAO
{
  private JDBCController jdbcController;
  private static  ReceptionDAO instance;

  private ReceptionDAO()
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

  public static synchronized ReceptionDAO getInstance()
  {
    if (instance == null)
    {
      instance = new ReceptionDAO();
    }
    return instance;
  }
  @Override public void createPatient(String cprnumber, String firstname,
      String lastname)
  {
    System.out.println("ReceptionDAO");
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO patient VALUES (?,?,?)");
      statement.setString(1,cprnumber);
      statement.setString(2,firstname);
      statement.setString(3,lastname);
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<Role> HCSGetRoles()
  {
    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHH");
    ArrayList<Role> roles=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM userslogin  ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String username = resultSet.getString("username");
        String password= resultSet.getString("passwords");
        String role=resultSet.getString("roles");
        roles.add(new Role(firstname,lastname,birthday,username,password,role));
        System.out.println(roles.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return roles;
  }
}
