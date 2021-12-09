package HCS.client.model;

import HCS.client.network.PrescriptionClient;
import HCS.shared.transferObjects.Prescription;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PrescriptionModelImpl implements PrescriptionModel
{
  private PropertyChangeSupport support;
  private PrescriptionClient prescriptionClient;

  public PrescriptionModelImpl(PrescriptionClient prescriptionClient)
  {
    this.prescriptionClient=prescriptionClient;
    this.support= new PropertyChangeSupport(this);
    prescriptionClient.startClient();
    prescriptionClient.addListener("HCSGetBookings",this::fireforward);
    prescriptionClient.addListener("HCSGetPrescriptions",this::fireforward);
  }

  private void fireforward(PropertyChangeEvent event)
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
}
