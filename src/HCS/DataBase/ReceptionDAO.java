package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
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
  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionDAO");
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO patient VALUES (?,?,?,?,?,?,?,?)");
      statement.setString(1,patient.getCprNumber());
      statement.setString(2,patient.getFirstname());
      statement.setString(3,patient.getLastname());
      statement.setDate(4,patient.getBirthday());
      statement.setString(5,patient.getSex());
      statement.setString(6,patient.getAddress());
      statement.setString(7,patient.getPhone());
      statement.setString(8,patient.getMail());

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
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM userlogin  ");

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

  @Override public ArrayList<Patient> HCSGetPatients()
  {
    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHH");
    ArrayList<Patient> patients=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM userlogin  ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String cprNumber = resultSet.getString("cprNumber");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String sex = resultSet.getString("sex");
        String address= resultSet.getString("address");
        String phone=resultSet.getString("phone");
        String mail=resultSet.getString("mail");
        patients.add(new Patient(cprNumber,firstname,lastname,birthday,sex,address,phone,mail));
       // System.out.println(patients.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return patients;
  }
}
