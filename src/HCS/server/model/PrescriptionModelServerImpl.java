package HCS.server.model;

import HCS.DataBase.ManageLoginDAO;
import HCS.DataBase.ManagePatientDAO;
import HCS.DataBase.ManagePrescriptionDAO;
import HCS.shared.transferObjects.Prescription;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class PrescriptionModelServerImpl implements PrescriptionModelServer
{
  private PropertyChangeSupport support;
  private ManagePrescriptionDAO prescriptionDAO;

  public PrescriptionModelServerImpl(ManagePrescriptionDAO prescriptionDAO)
  {
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

  @Override public void createPrescription(Prescription prescription)
  {
    prescriptionDAO.createPrescription(prescription);
  }

  @Override public ArrayList<Prescription> getPrescriptions()
  {
    return prescriptionDAO.getPrescriptions();
  }

  @Override public void removePrescription(Prescription prescription)
  {
    prescriptionDAO.removePrescription(prescription);
  }

  @Override public void updatePrescription(Date bookingDate, String bookingTime,
      String prescriptionType, String newPrescriptionType,
      String prescriptionText)
  {
    prescriptionDAO.updatePrescription(bookingDate, bookingTime, prescriptionType, newPrescriptionType, prescriptionText);
  }

  @Override public ArrayList<Prescription> getPrescriptionsByPatient(
      String cprNumber)
  {
    return null;
  }

  @Override public ArrayList<Prescription> getPrescriptionsByDate()
  {
    return null;
  }
}
