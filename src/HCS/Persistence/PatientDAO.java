package HCS.Persistence;

import HCS.shared.transferObjects.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientDAO implements ManagePatientDAO
{
  private JDBCController jdbcController;
  private static PatientDAO instance;

  private PatientDAO()
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

  public static synchronized PatientDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PatientDAO();
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

  @Override public void removePatient(String cprNumber)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM patient where cprNumber=?");
      statement.setString(1,cprNumber);
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("UPDATE  patient  SET cprNumber=?, firstname=?, lastname=?, birthday=?, sex=?, address=?, phone=?, mail=? where cprNumber=?");
      statement.setString(1,patient.getCprNumber());
      statement.setString(2,patient.getFirstname());
      statement.setString(3,patient.getLastname());
      statement.setDate(4,patient.getBirthday());
      statement.setString(5,patient.getSex());
      statement.setString(6,patient.getAddress());
      statement.setString(7,patient.getPhone());
      statement.setString(8,patient.getMail());
      statement.setString(9,cprNumber);

      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

  }


  @Override public ArrayList<Patient> GetPatients()
  {
    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHH");
    ArrayList<Patient> patients=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient  ");

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

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
  {
    ArrayList<Patient> patients=new ArrayList<>();
    ///

    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient WHERE cprNumber LIKE ? OR firstname LIKE ? OR lastname LIKE ?   ");
      statement.setString(1,search+"%");
      statement.setString(2,search+"%");
      statement.setString(3,search+"%");
    //  statement.setString(2,search);
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

  @Override public boolean patientExist(String cprNumber)
  {

      boolean exist=false;

      try(Connection connection = jdbcController.getConnection()) {


        PreparedStatement statement = connection.prepareStatement
            ("SELECT cprnumber FROM patient WHERE cprnumber = ?");
        statement.setString(1,cprNumber);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
          String a=resultSet.getString("cprnumber");
          System.out.println(a);
          if (a.equals(cprNumber))
            exist = true;
        }

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

      return exist;
    }


}
