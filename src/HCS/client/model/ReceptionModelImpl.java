package HCS.client.model;

import HCS.client.network.ReceptionClient;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class ReceptionModelImpl implements ReceptionModel
{
  private ReceptionClient clientReception;
  private PropertyChangeSupport support;

  public ReceptionModelImpl(ReceptionClient clientReception)
  {
    this.clientReception=clientReception;
    support = new PropertyChangeSupport(this);
    clientReception.startClient();
    clientReception.addListener("HCSGetPatients",this::fireAll);
    clientReception.addListener("HCSGetRoles", this::fireAll);
    clientReception.addListener("HCSGetBookings",this::fireAll);

  }

  private void fireAll(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionModel");
   clientReception.createPatient(patient);
   clientReception.HCSGetPatients();
  }
  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override
  public void removeListener(String eventName, PropertyChangeListener listener) {
    support.removePropertyChangeListener(eventName, listener);
  }
  @Override public void HCSGetRoles()
  {
    System.out.println("model");
    clientReception.HCSGetRoles();
  }

  @Override public void HCSGetPatients()
  {
    clientReception.HCSGetPatients();
  }

  @Override public void HCSGetSpecificPatients(String search)
  {
    clientReception.HCSGetSpecificPatients(search);
  }

  @Override public void createBooking(Booking booking)
  {
    System.out.println("BookingModel");
    clientReception.createBooking(booking);
    clientReception.HCSGetBookings();
  }

  @Override public void HCSGetBookings()
  {
    clientReception.HCSGetBookings();
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    clientReception.removeBooking(bookingDate, bookingTime);
  }

  @Override public ArrayList<String> getTimeAvailable(Date date)
  {
    return clientReception.getTimeAvailable(date);
  }
}
