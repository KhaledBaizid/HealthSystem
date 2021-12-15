package HCS.client.ViewModel;

import HCS.client.model.UserModel;
import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class AdminViewModel implements Subject
{
  private PropertyChangeSupport support;
  private UserModel model;
  private ObservableList<User> roles1;


  public AdminViewModel(UserModel model)
  {
    this.model=model;
    support= new PropertyChangeSupport(this);
    roles1= FXCollections.observableArrayList();
    model.addListener("HCSGetRoles",this::getUsers);

  }

  private void getUsers(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<User> users =(ArrayList<User>) event.getNewValue();
    roles1.addAll(users);
  }

  public  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role){
    if (model.UserExist(username))
      support.firePropertyChange("usernameExists",null,true);
     else
    model.CreateUser(firstname, lastname, birthday, username, password, role);
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
    if (!username.equals(user.getUsername()))
    {
      if (model.UserExist(user.getUsername()))
      {
        support.firePropertyChange("usernameExists",null,true);

      }
      else  model.updateUser(username, user);
    } else


    model.updateUser(username, user);
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
