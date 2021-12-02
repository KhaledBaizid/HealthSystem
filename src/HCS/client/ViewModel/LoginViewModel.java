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
 // private ModelInterface model;
  private LoginModel model;
  private StringProperty error;

  public LoginViewModel(LoginModel model)
  {support = new PropertyChangeSupport(this);
    this.model=model;
    model.addListener("HCSLogin", this::firePropertyForward);
    //model.addListener(RequestType.EXISTING_USERNAME.toString(), this::updateErrorLabel);
    this.error = new SimpleStringProperty("");
  }

  private void firePropertyForward(PropertyChangeEvent event)
  {
    if (event.getNewValue()!=null)
    support.firePropertyChange(event);
    else error.set("Check Again");

  }

  public void HCSLogin(String username,String password)
  {
    model.HCSLogin(username, password);
    System.out.println("NEWLOGIN");
  }

  /*public void getRoles()
  {
    model.HCSGetRoles();
  }*/

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
}
