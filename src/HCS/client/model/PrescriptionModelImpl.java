package HCS.client.model;

import HCS.client.network.PrescriptionClient;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class PrescriptionModelImpl implements PrescriptionModel
{
  private PropertyChangeSupport support;
  private PrescriptionClient prescriptionClient;

  public PrescriptionModelImpl(PrescriptionClient prescriptionClient)
  {
    this.prescriptionClient=prescriptionClient;
    this.support= new PropertyChangeSupport(this);
    prescriptionClient.startClient();
    prescriptionClient.addListener("HCSGetBookings",this::fireBookings);
    prescriptionClient.addListener("HCSGetPrescriptions",this::firePrescriptions);
  }

  private void fireBookings(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  private void firePrescriptions(PropertyChangeEvent event)
  {support.firePropertyChange(event);
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

 /* @Override public void HCSGetBookings()
  {
    clientDoctor.HCSGetBookings();
  }*/

  @Override public void createPrescription(Prescription prescription)
  {
    prescriptionClient.createPrescription(prescription);
  }

  @Override public void getPrescriptions()
  {
    prescriptionClient.getPrescriptions();
  }

  @Override public void removePrescription(Prescription prescription)
  {
    prescriptionClient.removePrescription(prescription);
    prescriptionClient.getPrescriptions();
  }

  @Override public void updatePrescription(Date bookingDate, String bookingTime,
      String prescriptionType, String newPrescriptionType,
      String prescriptionText)
  {
    prescriptionClient.updatePrescription(bookingDate, bookingTime, prescriptionType, newPrescriptionType, prescriptionText);
    prescriptionClient.getPrescriptions();
  }

  @Override public void getPrescriptionsByPatient(
      String cprNumber)
  {
     prescriptionClient.getPrescriptionsByPatient(cprNumber);
  }

  @Override public void getPrescriptionsByDate(Date date)
  {
    prescriptionClient.getPrescriptionsByDate(date);
  }

  @Override public ArrayList<String> getPrescriptionsType()
  {
    return prescriptionClient.getPrescriptionsType();
  }
}
