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
   // if (!patientDAO.patientExist(patient.getCprNumber()))
    patientDAO.createPatient(patient);
    //  userDAO.createPatient(cprnumber, firstname, Lastname);

  }

  @Override public void removePatient(String cprNumber)
  {
    if( !bookingDAO.isPatientHasABooking(cprNumber))
    {
      patientDAO.removePatient(cprNumber);
    }
  /*  else
    {
      String s="h";
      support.firePropertyChange("PtientHasBooking",null,s);
    }*/
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    patientDAO.updatePatient(cprNumber, patient);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
    // support.firePropertyChange("HCSGetPrescriptions",null,booking1);

  }

  @Override public ArrayList<Patient> GetPatients()
  {
    ArrayList<Patient> patients;
    patients=patientDAO.GetPatients();
    support.firePropertyChange("HCSGetPatients",null, patients);
    return patients;
  }

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
  {
    ArrayList<Patient> patients;
    patients=patientDAO.GetSpecificPatients(search);
    return patients;
  }

}
