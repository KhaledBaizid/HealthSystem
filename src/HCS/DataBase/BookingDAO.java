package HCS.DataBase;

import HCS.shared.transferObjects.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAO implements ManageBookingDAO
{
  private JDBCController jdbcController;
  private static  BookingDAO instance;

  private BookingDAO()
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

  public static synchronized BookingDAO getInstance()
  {
    if (instance == null)
    {
      instance = new BookingDAO();
    }
    return instance;
  }


  @Override public void createBooking(Booking booking)
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

  @Override public ArrayList<Booking> GetBookings()
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

  @Override public void updateBooking(Date bookingDate, String bookingTime, Booking booking)
  {
    try(Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("UPDATE  booking  SET bookingdate = ?, bookingtime = ?, symptoms = ?  where bookingdate = ? AND bookingtime = ?");
      statement.setDate(1,booking.getBookingDate());
      statement.setString(2,booking.getBookingTime());
      statement.setString(3,booking.getSymptoms());
      statement.setDate(4,bookingDate);
      statement.setString(5,bookingTime);

      statement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }



  }

  @Override public ArrayList<String> getAvailableTime(Date date)
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

  }

  @Override public boolean bookingExist(Date bookingDate, String bookingTime)
  {
    boolean exist=false;
    //System.out.println("CREATEUSERDATABASE");
    try(Connection connection = jdbcController.getConnection()) {
      System.out.println(bookingDate.toString());
      System.out.println(bookingTime);
      // System.out.println(username+""+password);
      PreparedStatement statement = connection.prepareStatement
          ("SELECT bookingtime FROM booking WHERE bookingdate = ? AND bookingtime = ? ");
      statement.setDate(1,bookingDate);
      statement.setString(2,bookingTime);
      // statement.setString(2,password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String a=resultSet.getString("bookingtime");
       // a="08:15";
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

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    ArrayList<Booking> bookings= GetBookings();
    boolean find = false;
    for (Booking i:bookings )
    {
      if (i.getCprNumber().equals(cprNumber))
      {
        find=true;
        break;
      }
    }
  
    return find;
  }

  @Override public ArrayList<Booking> GetBookingsBYCprNumber(String cprNumber1)
  {
    ArrayList<Booking> bookings=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient inner join booking on patient.cprNumber=booking.cprNumber WHERE booking.cprNumber LIKE ? ");
      statement.setString(1,cprNumber1+"%");
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

  @Override public ArrayList<Booking> GetPatientBookingsByDate(Date date)
  {
    ArrayList<Booking> bookings=new ArrayList<>();
    ///
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient inner join booking on patient.cprNumber=booking.cprNumber WHERE booking.bookingdate= ? ");
      statement.setDate(1,date);
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
}
