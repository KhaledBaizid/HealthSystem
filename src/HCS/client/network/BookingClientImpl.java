package HCS.client.network;

import HCS.server.network.RMIServer;
import HCS.shared.BookingClientCallBack;
import HCS.shared.transferObjects.Booking;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

public class BookingClientImpl
    implements BookingClient, BookingClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;

  public BookingClientImpl()
  {
    this.support=new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");
      server.registerClient(this);
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override public void createBooking(Booking booking)
  {
    try
    {
      System.out.println("BookingClient");
      server.createBooking(booking);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void GetBookings()
  {
    try
    { ArrayList<Booking> bookings;
      bookings= server.GetBookings();
      support.firePropertyChange("HCSGetBookings",null,bookings);

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    try
    {
      server.removeBooking(bookingDate, bookingTime);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void updateBooking(Date bookingDate, String bookingTime,
      Booking booking)
  {
    try
    {
      server.updateBooking(bookingDate, bookingTime, booking);
    }
    catch (RemoteException e)
    {

    }
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    try
    {
      return server.getAvailableTime(date);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void GetBookingsBYCprNumber(String cprNumber)
  {
    try
    { ArrayList<Booking> bookings;
      bookings= server.GetBookingsBYCprNumber(cprNumber);
      support.firePropertyChange("HCSGetBookings",null,bookings);
    // return bookings;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
   // return null;
  }

  @Override public void GetBookingsByDate(Date date)
  {
    try
    { ArrayList<Booking> bookings;
      bookings= server.GetBookingsByDate(date);
      support.firePropertyChange("HCSGetBookings",null,bookings);
      // return bookings;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    try
    {
      return server.isPatientHasABooking(cprNumber);


    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean isBookingHasAPrescription(Date bookingDate,
      String bookingTime)
  {
    try
    {
      return server.isBookingHasAPrescription(bookingDate, bookingTime);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void Disconnect()
  {
    try
    {
      server.unregisterBookingClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
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



  @Override public void sharedBookings(PropertyChangeEvent event)
      throws RemoteException
  {
    support.firePropertyChange(event);
  }
}
