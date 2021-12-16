package HCS.server.model;

import HCS.Persistence.ManageBookingDAO;
import HCS.Persistence.ManagePatientDAO;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PatientModelServerImpl implements PatientModelServer
{
  private PropertyChangeSupport support;
  private ManagePatientDAO patientDAO;
  private ManageBookingDAO bookingDAO;

  public PatientModelServerImpl(ManagePatientDAO patientDAO,ManageBookingDAO bookingDAO)
  {
    this.patientDAO=patientDAO;
    this.bookingDAO=bookingDAO;

    support = new PropertyChangeSupport(this);

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

  @Override public boolean patientExist(String cprNumber)
  {
    return patientDAO.patientExist(cprNumber);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionModelServer");

    patientDAO.createPatient(patient);
    ArrayList<Patient> patients;
    patients=patientDAO.GetPatients();
    support.firePropertyChange("HCSGetPatients",null, patients);


  }

  @Override public void removePatient(String cprNumber)
  {
    if( !bookingDAO.isPatientHasABooking(cprNumber))
    {
      patientDAO.removePatient(cprNumber);

      ArrayList<Patient> patients;
      patients=patientDAO.GetPatients();
      support.firePropertyChange("HCSGetPatients",null, patients);
    }

  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    patientDAO.updatePatient(cprNumber, patient);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();

    ArrayList<Patient> patients;
    patients=patientDAO.GetPatients();
    support.firePropertyChange("HCSGetPatients",null, patients);

    support.firePropertyChange("HCSGetBookings",null,booking1);


  }

  @Override public ArrayList<Patient> GetPatients()
  {
    ArrayList<Patient> patients;
    patients=patientDAO.GetPatients();

    return patients;
  }

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
  {
    ArrayList<Patient> patients;
    patients=patientDAO.GetSpecificPatients(search);
    return patients;
  }

}
