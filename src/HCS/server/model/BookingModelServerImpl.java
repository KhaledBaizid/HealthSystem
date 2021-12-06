package HCS.server.model;

import HCS.DataBase.ManageBookingDAO;
import HCS.DataBase.ManageLoginDAO;
import HCS.shared.transferObjects.Booking;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class BookingModelServerImpl implements BookingModelServer
{

  private PropertyChangeSupport support;
  private ManageBookingDAO bookingDAO;

  public BookingModelServerImpl(ManageBookingDAO bookingDAO)
  {
    this.bookingDAO=bookingDAO;
    support=new PropertyChangeSupport(this);
  }
  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);

  }


  @Override public void createBooking(Booking booking)
  {
    System.out.println("BookingServerModel");
    bookingDAO.createBooking(booking);
    /////////
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
  }

  @Override public ArrayList<Booking> GetBookings()
  {
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    bookingDAO.removeBooking(bookingDate, bookingTime);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingDAO.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetPatientBookings(String cprNumber)
  {
    // return bookingDAO.GetPatientBookings(cprNumber);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetPatientBookings(cprNumber);
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public ArrayList<Booking> GetPatientBookingsByDate(Date date)
  {
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetPatientBookingsByDate(date);
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    return bookingDAO.isPatientHasABooking(cprNumber);
  }

}
