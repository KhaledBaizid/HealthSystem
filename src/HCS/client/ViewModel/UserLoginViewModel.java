package HCS.client.ViewModel;

import HCS.client.model.HCSModelAdminInterface;
import HCS.shared.transferObjects.RequestType;
import HCS.shared.utility.Subject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserLoginViewModel implements Subject
{
  private HCSModelAdminInterface model;
  private PropertyChangeSupport support;
  private StringProperty error;
  public UserLoginViewModel(HCSModelAdminInterface model)
  {  error= new SimpleStringProperty();
    support = new PropertyChangeSupport(this);
    this.model=model;
    model.addListener(RequestType.SUCCESSFUL_LOGIN.toString(), this::firePropertyForward);
    model.addListener(RequestType.EXISTING_USERNAME.toString(), this::updateErrorLabel);
  }
  public void login(String username) {
    model.login(username);
    System.out.println("ViewModelLogin");
  }

  private void updateErrorLabel(PropertyChangeEvent event) {
    Platform.runLater(() -> {
      error.setValue("Username Exists....");

    });
  }
  public  StringProperty errorProperty(){
    return error;
  }

  private void firePropertyForward(PropertyChangeEvent event) {
    support.firePropertyChange(event);
  }
  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {

  }
}

