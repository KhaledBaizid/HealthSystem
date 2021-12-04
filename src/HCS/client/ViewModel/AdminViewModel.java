package HCS.client.ViewModel;

import HCS.client.model.AdminModel;
import HCS.shared.transferObjects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AdminViewModel
{
  private AdminModel model;
  private ObservableList<User> roles1;


  public AdminViewModel(AdminModel model)
  {
    this.model=model;
    roles1= FXCollections.observableArrayList();
    model.addListener("HCSGetRoles",this::getRoles);

  }

  private void getRoles(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<User> users =(ArrayList<User>) event.getNewValue();
    roles1.addAll(users);
  }

  public  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role){
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

}
