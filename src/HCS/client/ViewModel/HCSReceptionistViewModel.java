package HCS.client.ViewModel;

import HCS.client.model.HCSModelReceptionInterface;
import HCS.shared.transferObjects.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class HCSReceptionistViewModel
{
  private HCSModelReceptionInterface model;
  private ObservableList<Role> roles1;

  public HCSReceptionistViewModel(HCSModelReceptionInterface model)
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
  public ObservableList<Role> getTableViewRoles()
  {

    return roles1;
  }
  public void getModelRoles()
  {
    System.out.println("viewmodel");
    model.HCSGetRoles();
  }

  public void createPatient(String cprnumber ,String firstname,String Lastname)
  {
    System.out.println("ReceptionViewModel");
    model.createPatient(cprnumber, firstname, Lastname);

  }
}
