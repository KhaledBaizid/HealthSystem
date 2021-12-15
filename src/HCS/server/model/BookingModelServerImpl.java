package HCS.server.model;

import HCS.Persistence.ManageBookingDAO;
import HCS.Persistence.PrescriptionDAO;
import HCS.shared.transferObjects.Booking;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class BookingModelServerImpl implements BookingModelServer
{

  private PropertyChangeSupport support;
  private ManageBookingDAO bookingDAO;
  private PrescriptionDAO prescriptionDAO;

  public BookingModelServerImpl(ManageBookingDAO bookingDAO,PrescriptionDAO prescriptionDAO)
  {
    this.bookingDAO=bookingDAO;
    this.prescriptionDAO=prescriptionDAO;
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

  @Override public void updateBooking(Date bookingDate, String bookingTime,
      Booking booking)
  {
    bookingDAO.updateBooking(bookingDate, bookingTime, booking);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();

    support.firePropertyChange("HCSGetBookings",null,booking1);

  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingDAO.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetBookingsBYCprNumber(String cprNumber)
  {
    // return bookingDAO.GetPatientBookings(cprNumber);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookingsBYCprNumber(cprNumber);
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public ArrayList<Booking> GetBookingsByDate(Date date)
  {
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookingsByDate(date);
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    return bookingDAO.isPatientHasABooking(cprNumber);
  }

  @Override public boolean isBookingHasAPrescription(Date bookingDate,
      String bookingTime)
  {
    return prescriptionDAO.isBookingHasAPrescription(bookingDate, bookingTime);
  }

}
