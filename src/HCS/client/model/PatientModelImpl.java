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
    patientClient.addListener("HCSGetPatients",this::fireForward);
   // patientClient.addListener("HCSGetRoles", this::fireForward);
    patientClient.addListener("HCSGetBookings",this::fireForward);
   // clientReception.addListener("PtientHasBooking",this::fireAll);
    patientClient.addListener("HCSGetPatients",this::fireForward);

  }

  private void fireForward(PropertyChangeEvent event)
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

  @Override public void Disconnect()
  {
    patientClient.Disconnect();
  }


}
