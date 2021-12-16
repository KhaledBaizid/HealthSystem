package HCS.client.ViewModel;

import HCS.client.model.UserModel;
import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminViewModel implements Subject
{
  private PropertyChangeSupport support;
  private UserModel model;
  private ObservableList<User> roles1;

  private StringProperty firstname;
  private StringProperty lastname;
  private StringProperty username;
  private StringProperty password;
  private ObjectProperty<LocalDate> birthday;
  private SimpleObjectProperty<String> role;

  public AdminViewModel(UserModel model)
  {
    this.model=model;
    support= new PropertyChangeSupport(this);
    firstname=new SimpleStringProperty("");
    lastname=new SimpleStringProperty("");
    username=new SimpleStringProperty("");
    password=new SimpleStringProperty("");
    birthday=new SimpleObjectProperty<LocalDate>();
    role= new SimpleObjectProperty<>();

    roles1= FXCollections.observableArrayList();
    model.addListener("HCSGetRoles",this::getUsers);

  }

  private void getUsers(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<User> users =(ArrayList<User>) event.getNewValue();
    roles1.addAll(users);
  }

  public  void CreateUser(/*String firstname,String lastname, Date birthday,String username,String password,String role*/){

    if (username.getValue().equals("") || password.getValue().equals(""))
    {
      support.firePropertyChange("emptyUsername", null, true);
    } else
    {
      if (model.UserExist(username.get()))
        support.firePropertyChange("usernameExists", null, true);
      else
      {
        model.CreateUser(firstname.get(), lastname.get(), Date.valueOf(birthday.get()), username.getValue(),
            password.getValue(), role.get());
        firstname.setValue("");
        lastname.setValue("");
        username.setValue("");
        password.setValue("");
      }
    }

  }

  public ObservableList<User> getTableViewRoles()
  {

    return roles1;
  }
  public void getModelUsers()
  {
    System.out.println("viewmodel");
    model.GetUsers();
  }

  public  void RemoveUser(String username){
    model.RemoveUser(username);
  }

  //boolean UserExist(String username);
  public void updateUser(String username, User user)
  {

    if (username.equals("") || user.getPassword().equals(""))
    {
      support.firePropertyChange("emptyUsername", null, true);
    } else
    {
      if (!username.equals(user.getUsername()))
      {
        if (model.UserExist(user.getUsername()))
        {
          support.firePropertyChange("usernameExists", null, true);

        }
        else
          model.updateUser(username, user);
      }
      else
        model.updateUser(username, user);
    }
  }

  public StringProperty getFirstname()
  {
    return firstname;
  }

  public StringProperty getLastname()
  {
    return lastname;
  }

  public StringProperty getPassword()
  {
    return password;
  }

  public ObjectProperty<LocalDate> getBirthday()
  {
    return birthday;
  }

  public SimpleObjectProperty getRole()
  {
    return role;
  }

  public StringProperty getUsername()
  {
    return username;
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

}
