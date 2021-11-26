package HCS.client.ViewModel;

import HCS.client.model.HCSModelAdminInterface;
import HCS.shared.transferObjects.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.util.ArrayList;

public class HCSAdminViewModel
{
  private HCSModelAdminInterface model;
  private ObservableList<Role> roles1;


  public HCSAdminViewModel(HCSModelAdminInterface model)
  {
    this.model=model;
    roles1= FXCollections.observableArrayList();
    model.addListener("HCSGetRoles",this::getRoles);

  }

  private void getRoles(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<Role> roles=(ArrayList<Role>) event.getNewValue();
    roles1.addAll(roles);
  }

  public  void HCSCreateRole(String firstname,String lastname, Date birthday,String username,String password,String role){
    model.HCSCreateRole(firstname, lastname, birthday, username, password, role);
  }

  public ObservableList<Role> getTableViewRoles()
  {

    return roles1;
  }
  public void getModelRoles()
  {
    System.out.println("viewmodel");
    model.HCSGetRoles();
  }

  public  void HCSRemoveRole(String username){
    model.HCSRemoveRole(username);
  }
}
