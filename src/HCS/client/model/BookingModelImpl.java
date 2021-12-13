package HCS.client.model;

import HCS.client.network.BookingClient;
import HCS.shared.transferObjects.Booking;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class BookingModelImpl implements BookingModel
{
  private PropertyChangeSupport support;
  private BookingClient clientBooking;
  public BookingModelImpl(BookingClient clientBooking)
  {
    this.clientBooking=clientBooking;
    this.support= new PropertyChangeSupport(this);
    clientBooking.startClient();
    clientBooking.addListener("HCSGetBookings",this::fireBookings);
    clientBooking.addListener("PtientHasBooking",this::firePatientHasBooking);
  }

  private void fireBookings(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  private void firePatientHasBooking(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public void createBooking(Booking booking)
  {
    System.out.println("BookingModel");
    clientBooking.createBooking(booking);
    clientBooking.GetBookings();
  }

  @Override public void GetBookings()
  {
    clientBooking.GetBookings();

  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    clientBooking.removeBooking(bookingDate, bookingTime);
  }

  @Override public void updateBooking(Date bookingDate, String bookingTime,
      Booking booking)
  {
    clientBooking.updateBooking(bookingDate, bookingTime, booking);
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return clientBooking.getAvailableTime(date);
  }

  @Override public void GetBookingsBYCprNumber(String cprNumber)
  {
    clientBooking.GetBookingsBYCprNumber(cprNumber);
  }

  @Override public void GetBookingsByDate(Date date)
  {
    clientBooking.GetBookingsByDate(date);
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    return clientBooking.isPatientHasABooking(cprNumber);
  }

  @Override public boolean isBookingHasAPrescription(Date bookingDate,
      String bookingTime)
  {
    return clientBooking.isBookingHasAPrescription(bookingDate, bookingTime);
  }
}
