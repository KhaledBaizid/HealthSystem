package HCS.client.model;

import HCS.client.network.PatientClient;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PatientModelImpl implements PatientModel
{
  private PatientClient patientClient;
  private PropertyChangeSupport support;

  public PatientModelImpl(PatientClient patientClient)
  {
    this.patientClient=patientClient;
    support = new PropertyChangeSupport(this);
    patientClient.startClient();
    patientClient.addListener("HCSGetPatients",this::fireAll);
    patientClient.addListener("HCSGetRoles", this::fireAll);
    patientClient.addListener("HCSGetBookings",this::fireAll);
   // clientReception.addListener("PtientHasBooking",this::fireAll);
    patientClient.addListener("HCSGetPatients",this::fireAll);

  }

  private void fireAll(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionModel");
    patientClient.createPatient(patient);
    patientClient.GetPatients();
  }

  @Override public void removePatient(String cprNumber)
  {
    patientClient.removePatient(cprNumber);
    patientClient.GetPatients();
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    patientClient.updatePatient(cprNumber, patient);
    patientClient.GetPatients();
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
    patientClient.HCSGetRoles();
  }

  @Override public void GetPatients()
  {
    patientClient.GetPatients();
  }

  @Override public void GetSpecificPatients(String search)
  {
    patientClient.GetSpecificPatients(search);
  }

  @Override public boolean patientExist(String cprNumber)
  {
    return patientClient.patientExist(cprNumber);
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
