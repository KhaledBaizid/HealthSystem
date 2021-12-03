package HCS.client.model;

import HCS.client.network.BookingClient;
import HCS.client.network.DoctorClient;
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
    clientBooking.addListener("HCSGetBookings",this::fireforward);
  }

  private void fireforward(PropertyChangeEvent event)
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
    clientBooking.HCSGetBookings();
  }

  @Override public void HCSGetBookings()
  {
    clientBooking.HCSGetBookings();

  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    clientBooking.removeBooking(bookingDate, bookingTime);
  }

  @Override public ArrayList<String> getTimeAvailable(Date date)
  {
    return clientBooking.getTimeAvailable(date);
  }
}
