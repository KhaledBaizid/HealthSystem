package HCS.client.model;

import HCS.client.network.LoginClient;
//import HCS.client.network.RMIClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelImpl implements LoginModel
{
  private LoginClient clientLogin;
 // private RMIClient rmiClient;
  private PropertyChangeSupport support;


  public LoginModelImpl(LoginClient clientLogin)
  {
    this.clientLogin=clientLogin;
    this.support=new PropertyChangeSupport(this);


        clientLogin.startClient();

    clientLogin.addListener("HCSLogin", this::fireRole);
  }


  private void fireRole(PropertyChangeEvent event)
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
