package HCS.client.model;

import HCS.client.network.DoctorClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DoctorModelImpl implements DoctorModel
{
  private PropertyChangeSupport support;
  private DoctorClient clientDoctor;

  public DoctorModelImpl(DoctorClient clientDoctor)
  {
    this.clientDoctor=clientDoctor;
    this.support= new PropertyChangeSupport(this);
    clientDoctor.startClient();
    clientDoctor.addListener("HCSGetBookings",this::fireforward);
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

  @Override public void HCSGetBookings()
  {
    clientDoctor.HCSGetBookings();
  }
}
