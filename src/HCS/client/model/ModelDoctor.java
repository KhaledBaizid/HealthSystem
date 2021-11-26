package HCS.client.model;

import HCS.client.network.HCSClientDoctor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelDoctor implements HCSModelDoctorInterface
{
  private PropertyChangeSupport support;
  private HCSClientDoctor clientDoctor;

  public ModelDoctor(HCSClientDoctor clientDoctor)
  {
    this.clientDoctor=clientDoctor;
    this.support= new PropertyChangeSupport(this);
    clientDoctor.startClient();
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
}
