package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;

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

  @Override public ArrayList<Patient> HCSGetSpecificPatients(String search)
  {
    ArrayList<Patient> patients=new ArrayList<>();
    ///

    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient WHERE cprNumber LIKE ? OR firstname LIKE ? OR lastname LIKE ?   ");
      statement.setString(1,"%"+search+"%");
      statement.setString(2,"%"+search+"%");
      statement.setString(3,"%"+search+"%");
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
      //System.out.println("CREATEUSERDATABASE");
      try(Connection connection = jdbcController.getConnection()) {

        // System.out.println(username+""+password);
        PreparedStatement statement = connection.prepareStatement
            ("SELECT cprnumber FROM patient WHERE cprnumber = ?");
        statement.setString(1,cprNumber);
        // statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
          String a=resultSet.getString("cprnumber");
          System.out.println(a);
          if (a.equals(cprNumber))
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


 /* @Override public void createBooking(Booking booking)
  {
    try(Connection connection = jdbcController.getConnection()) {
      System.out.println("BookingDAO");
      PreparedStatement statement = connection.prepareStatement("INSERT INTO booking VALUES (?,?,?,?)");
      statement.setDate(1,booking.getBookingDate());
      statement.setString(2,booking.getBookingTime());
      statement.setString(3,booking.getSymptoms());
      statement.setString(4,booking.getCprNumber());
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<Booking> HCSGetBookings()
  {
    ArrayList<Booking> bookings=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient inner join booking on patient.cprNumber=booking.cprNumber  ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Date bookingDate = resultSet.getDate("bookingdate");
        String bookingTime = resultSet.getString("bookingtime");
        String cprNumber = resultSet.getString("cprNumber");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String sex = resultSet.getString("sex");
        String symptoms= resultSet.getString("symptoms");

        bookings.add(new Booking(bookingDate,bookingTime,cprNumber,firstname,lastname,birthday,sex,symptoms));
        // System.out.println(patients.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return bookings;
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM booking where bookingdate=? AND bookingtime=?");
      statement.setDate(1,bookingDate);
      statement.setString(2,bookingTime);
      statement.executeUpdate();



    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<String> getTimeAvailable(Date date)
  {
    ArrayList<String> timeAvalable = new ArrayList<>();
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT bookingtime FROM booking WHERE bookingdate=? ");
      statement.setDate(1,date);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {

       // assert timeAvalable != null;
        timeAvalable.add(resultSet.getString("bookingtime"));
        // System.out.println(patients.get(0).getUsername());
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }


    return timeAvalable;
  }*/
}
