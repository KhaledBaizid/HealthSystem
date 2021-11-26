package HCS.client.model;

import HCS.client.network.HCSClientLogin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelLogin implements HCSModelLoginInterface
{
  private HCSClientLogin clientLogin;
  private PropertyChangeSupport support;

  public ModelLogin(HCSClientLogin clientLogin)
  {
    this.clientLogin=clientLogin;
    this.support=new PropertyChangeSupport(this);
    clientLogin.startClient();
    clientLogin.addListener("HCSLogin", this::fireAll);
  }

  private void fireAll(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public void HCSLogin(String username, String password)
  {
   clientLogin.HCSLogin(username, password);
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
