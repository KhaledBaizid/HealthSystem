package HCS.client.model;

import HCS.client.network.PatientClient;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PatientModelImpl implements PatientModel
{
  private PatientClient clientReception;
  private PropertyChangeSupport support;

  public PatientModelImpl(PatientClient clientReception)
  {
    this.clientReception=clientReception;
    support = new PropertyChangeSupport(this);
    clientReception.startClient();
    clientReception.addListener("HCSGetPatients",this::fireAll);
    clientReception.addListener("HCSGetRoles", this::fireAll);
    clientReception.addListener("HCSGetBookings",this::fireAll);
   // clientReception.addListener("PtientHasBooking",this::fireAll);
    clientReception.addListener("HCSGetPatients",this::fireAll);

  }

  private void fireAll(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionModel");
   clientReception.createPatient(patient);
   clientReception.GetPatients();
  }

  @Override public void removePatient(String cprNumber)
  {
    clientReception.removePatient(cprNumber);
    clientReception.GetPatients();
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    clientReception.updatePatient(cprNumber, patient);
    clientReception.GetPatients();
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

  @Override public void GetPatients()
  {
    clientReception.GetPatients();
  }

  @Override public void GetSpecificPatients(String search)
  {
    clientReception.GetSpecificPatients(search);
  }

 /* @Override public void createBooking(Booking booking)
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
  }*/
}
