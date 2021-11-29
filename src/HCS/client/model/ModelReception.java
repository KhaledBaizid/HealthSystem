package HCS.client.model;

import HCS.client.network.HCSClientReception;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelReception implements HCSModelReceptionInterface
{
  private HCSClientReception clientReception;
  private PropertyChangeSupport support;

  public ModelReception(HCSClientReception clientReception)
  {
    this.clientReception=clientReception;
    support = new PropertyChangeSupport(this);
    clientReception.startClient();
    clientReception.addListener("HCSGetRoles", this::fireAll);

  }

  private void fireAll(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionModel");
   clientReception.createPatient(patient);
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
}
