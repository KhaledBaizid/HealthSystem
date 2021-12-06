package HCS.server.model;

import HCS.DataBase.ManageLoginDAO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelServerImpl implements LoginModelServer


{

  private PropertyChangeSupport support;
  private ManageLoginDAO loginDAO;
  public LoginModelServerImpl(ManageLoginDAO loginDAO)
  {
    this.loginDAO=loginDAO;
    support = new PropertyChangeSupport(this);
  }
  @Override public String Login(String username, String password)
  {
    return loginDAO.Login(username, password);
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
}
