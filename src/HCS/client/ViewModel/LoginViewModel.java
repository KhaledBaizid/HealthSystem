package HCS.client.ViewModel;

import HCS.client.model.LoginModel;
import HCS.shared.utility.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements Subject
{

  private PropertyChangeSupport support;

  private LoginModel model;
  private StringProperty error;
  private StringProperty username;
  private StringProperty password;

  public LoginViewModel(LoginModel model)
  {support = new PropertyChangeSupport(this);
    this.model=model;
    model.addListener("HCSLogin", this::fireRole);

    this.error = new SimpleStringProperty("");
    this.username=new SimpleStringProperty("");
    this.password=new SimpleStringProperty("");
  }

  private void fireRole(PropertyChangeEvent event)
  {
    if (event.getNewValue()!=null)
    support.firePropertyChange(event);
    else error.set("Username and Password do not match");

  }

  public void HCSLogin(String username,String password)
  {
    model.HCSLogin(username, password);
    System.out.println("NEWLOGIN");
  }

  public void HCSLogin()
  {
    model.HCSLogin(username.get(), password.get());
    System.out.println("NEWLOGIN");
  }



  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {

  }
  public StringProperty errorProperty()
  {
    return error;
  }
  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }
}
