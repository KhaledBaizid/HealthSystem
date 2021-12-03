package HCS.client.view.ADMIN;

import HCS.client.ViewModel.AdminViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Role;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;

public class HCSAdminController
{
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField birthday;
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private DatePicker birthdayDatePicker;
  @FXML
  private TextField role;

  @FXML
  private ComboBox roleComboBox;
  @FXML
  private TableView<Role> RoleTableView;

  @FXML private
  TableColumn<Role, String> firstnameColumn;
  @FXML private
  TableColumn<Role, String> lastnameColumn;
  @FXML private
  TableColumn<Role, Date> birthdayColumn;
  @FXML private
  TableColumn<Role, String> usernameColumn;
  @FXML private
  TableColumn<Role, String> passwordColumn;
  @FXML private
  TableColumn<Role, String> roleColumn;







  private ViewHandler vh;
  private AdminViewModel vm;
  public void init(ViewHandler vh, AdminViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;
   // vm.addListener("HCSLogin",this::succesfulLogin);
    roleComboBox.getItems().addAll("ADMIN","RECEPTION","DOCTOR");
    ////
   // getRoles();
    firstnameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("firstname"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("lastname"));
    birthdayColumn.setCellValueFactory(new PropertyValueFactory<Role,Date>("birthday"));
    usernameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("username"));
    passwordColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("password"));
    roleColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("role"));
    vm.getModelUsers();
    RoleTableView.setItems(vm.getTableViewRoles());


    ///

  }

  private void getRoles()
  {
    vm.getModelUsers();
  }

  public void createRoleButton()
  {
      LocalDate localDate=birthdayDatePicker.getValue();
      Date date=Date.valueOf(localDate);
    vm.CreateUser(firstname.textProperty().getValue(),
                     lastname.textProperty().getValue(),
       // birthdayDatePicker.getValue()
        date,
        username.textProperty().getValue(),
        password.textProperty().getValue(),
        roleComboBox.getSelectionModel().getSelectedItem().toString());
   // vm.getModelRoles();
  }

  public void deleteRoleButton()
  {
   // LocalDate s= birthdayDatePicker.getValue();
   // System.out.println(s.toString());
   // System.out.println(HCSAdminController.class.getName());
    vm.RemoveUser(username.textProperty().getValue());
     vm.getModelUsers();
    System.out.println("controller");
    //vh.openHCSAdminDeleteRole();

  }
 public void  TableViewClicked()
 { Role role=null;
    role =RoleTableView.getSelectionModel().getSelectedItem();
   firstname.setText(role.getFirstname());
   lastname.setText(role.getLastname());
   //Date date =role.getBirthday();
 // LocalDate localdate= date.toLocalDate();
   birthdayDatePicker.setValue(role.getBirthday().toLocalDate());
   username.setText(role.getUsername());
   password.setText(role.getPassword());
   roleComboBox.getSelectionModel().select(role.getRole());
   // System.out.println(role.getUsername());
 }
}
