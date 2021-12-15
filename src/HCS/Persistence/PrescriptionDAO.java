package HCS.Persistence;

import HCS.shared.transferObjects.Prescription;

import java.sql.*;
import java.util.ArrayList;

public class PrescriptionDAO implements ManagePrescriptionDAO
{
  private JDBCController jdbcController;
  private static PrescriptionDAO instance;
  private Object Text;

  private PrescriptionDAO()
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

  public static synchronized PrescriptionDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PrescriptionDAO();
    }
    return instance;
  }

  @Override public ArrayList<String> getPrescreptionsType()
  {
    ArrayList<String> prescriptionsType = new ArrayList<>();

    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM prescriptionType ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {

        // assert timeAvalable != null;
        prescriptionsType.add(resultSet.getString("typeName"));
        // System.out.println(patients.get(0).getUsername());
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }


    return prescriptionsType;
  }

  @Override public void createPrescription(Prescription prescription)
  {
   // Text=prescriptionText;
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO  prescription VALUES (?,?,?,?)");
      statement.setDate(1,prescription.getBookingDate());
      statement.setString(2,prescription.getBookingTime());
      statement.setString(3,prescription.getPrescriptionType());
   //   statement.setObject(4, Text);
      statement.setString(4, prescription.getPrescriptionText());


      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public String getSpecificPrescription(Date bookingDate, String bookingTime, String prescriptionType)


  {
    String prescriptionContent="";
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM prescription WHERE bookingdate = ? AND bookingtime = ? AND typeName = ?  ");
      statement.setDate(1,bookingDate);
      statement.setString(2,bookingTime);
      statement.setString(3,prescriptionType);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        prescriptionContent = resultSet.getString("prescriptionText");

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return prescriptionContent;
  }

  @Override public ArrayList<Prescription> getPrescriptions()
  {
    ArrayList<Prescription> prescriptions=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(" SELECT patient.cprnumber,patient.firstname,patient.lastname,patient.birthday,patient.sex,booking.bookingdate,booking.bookingtime,booking.symptoms,prescription.typename,prescription.prescriptiontext \n"
          + "FROM prescription INNER JOIN booking on prescription.bookingdate=booking.bookingdate and prescription.bookingtime=booking.bookingtime\n"
          + "INNER JOIN patient on patient.cprNumber=booking.cprNumber    ORDER BY booking.bookingdate DESC , booking.bookingtime DESC ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Date bookingDate = resultSet.getDate("bookingdate");
        String bookingTime = resultSet.getString("bookingtime");
        String cprNumber = resultSet.getString("cprnumber");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String sex = resultSet.getString("sex");
        String symptoms= resultSet.getString("symptoms");
        String typeName=resultSet.getString("typename");
        String prescriptionText=resultSet.getString("prescriptiontext");


        prescriptions.add(new Prescription(bookingDate,bookingTime,cprNumber,firstname,lastname,birthday,sex,symptoms,typeName,prescriptionText));
      //  System.out.println(symptoms);
        // System.out.println(patients.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return prescriptions;
  }

  @Override public boolean isBookingHasAPrescription(Date bookingDate,
      String bookingTime)
  {
    boolean exist=false;
    //System.out.println("CREATEUSERDATABASE");
    try(Connection connection = jdbcController.getConnection()) {

      // System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("SELECT * FROM prescription WHERE bookingdate = ? AND bookingtime = ?");
      statement.setDate(1,bookingDate);
      statement.setString(2,bookingTime);
      // statement.setString(2,password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String a=resultSet.getString("bookingtime");
        System.out.println(a);
        if (a.equals(bookingTime))
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

  @Override public void removePrescription(Prescription prescription)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM prescription where bookingdate = ? AND bookingtime = ? AND typeName = ?");
      statement.setDate(1,prescription.getBookingDate());
      statement.setString(2,prescription.getBookingTime());
      statement.setString(3,prescription.getPrescriptionType());
      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public void updatePrescription(Date bookingDate, String bookingTime,
      String prescriptionType, String newPrescriptionType,
      String prescriptionText)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("UPDATE  prescription  SET typeName = ?, prescriptionText = ? where bookingdate = ? AND bookingtime = ? AND typeName = ?");
      statement.setString(1,newPrescriptionType);
      statement.setString(2,prescriptionText);
      statement.setDate(3,bookingDate);
      statement.setString(4,bookingTime);
      statement.setString(5,prescriptionType);

      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

  }

  @Override public ArrayList<Prescription> getPrescriptionsByPatient(
      String cprNumber)
  {
    ArrayList<Prescription> prescriptions=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(" SELECT patient.cprnumber,patient.firstname,patient.lastname,patient.birthday,patient.sex,booking.bookingdate,booking.bookingtime,booking.symptoms,prescription.typename,prescription.prescriptiontext \n"
              + "FROM prescription INNER JOIN booking on prescription.bookingdate=booking.bookingdate and prescription.bookingtime=booking.bookingtime\n"
              + "INNER JOIN patient on patient.cprNumber=booking.cprNumber WHERE patient.cprnumber LIKE ?      ORDER BY booking.bookingdate DESC , booking.bookingtime DESC  ");
      statement.setString(1,cprNumber+"%");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Date bookingDate = resultSet.getDate("bookingdate");
        String bookingTime = resultSet.getString("bookingtime");
        String cprnumber = resultSet.getString("cprnumber");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String sex = resultSet.getString("sex");
        String symptoms= resultSet.getString("symptoms");
        String typeName=resultSet.getString("typename");
        String prescriptionText=resultSet.getString("prescriptiontext");

        prescriptions.add(new Prescription(bookingDate,bookingTime,cprnumber,firstname,lastname,birthday,sex,symptoms,typeName,prescriptionText));
        // System.out.println(patients.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return prescriptions;
  }

  @Override public ArrayList<Prescription> getPrescriptionsByDate(Date date)
  {
    ArrayList<Prescription> prescriptions=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(" SELECT patient.cprnumber,patient.firstname,patient.lastname,patient.birthday,patient.sex,booking.bookingdate,booking.bookingtime,booking.symptoms,prescription.typename,prescription.prescriptiontext \n"
          + "FROM prescription INNER JOIN booking on prescription.bookingdate=booking.bookingdate and prescription.bookingtime=booking.bookingtime\n"
          + "INNER JOIN patient on patient.cprNumber=booking.cprNumber WHERE booking.bookingdate = ?     ORDER BY booking.bookingdate DESC , booking.bookingtime DESC ");
      statement.setDate(1,date);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Date bookingDate = resultSet.getDate("bookingdate");
        String bookingTime = resultSet.getString("bookingtime");
        String cprnumber = resultSet.getString("cprnumber");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        Date birthday = resultSet.getDate("birthday");
        String sex = resultSet.getString("sex");
        String symptoms= resultSet.getString("symptoms");
        String typeName=resultSet.getString("typename");
        String prescriptionText=resultSet.getString("prescriptiontext");

        prescriptions.add(new Prescription(bookingDate,bookingTime,cprnumber,firstname,lastname,birthday,sex,symptoms,typeName,prescriptionText));
        // System.out.println(patients.get(0).getUsername());

      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    ///

    return prescriptions;
  }
}
