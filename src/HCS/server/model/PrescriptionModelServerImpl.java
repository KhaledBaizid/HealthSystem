package HCS.server.model;

import HCS.DataBase.ManageLoginDAO;
import HCS.DataBase.ManagePatientDAO;
import HCS.DataBase.ManagePrescriptionDAO;
import HCS.DataBase.ManagePrescriptionTypeDAO;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class PrescriptionModelServerImpl implements PrescriptionModelServer
{
  private PropertyChangeSupport support;
  private ManagePrescriptionDAO prescriptionDAO;
  private ManagePrescriptionTypeDAO prescriptionTypeDAO;

  public PrescriptionModelServerImpl(ManagePrescriptionDAO prescriptionDAO,ManagePrescriptionTypeDAO prescriptionTypeDAO)
  {
    this.prescriptionTypeDAO=prescriptionTypeDAO;
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
    return prescriptionDAO.getPrescriptionsByPatient(cprNumber);
  }

  @Override public ArrayList<Prescription> getPrescriptionsByDate(Date date)
  {
    return prescriptionDAO.getPrescriptionsByDate(date);
  }

  @Override public ArrayList<String> getPrescriptionsType()
  {
    return prescriptionTypeDAO.getPrescriptionsType();
  }
}
