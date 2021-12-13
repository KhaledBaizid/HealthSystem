package HCS.client.view.ADMIN;

import HCS.client.ViewModel.AdminViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.User;
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
  private TableView<User> RoleTableView;

  @FXML private
  TableColumn<User, String> firstnameColumn;
  @FXML private
  TableColumn<User, String> lastnameColumn;
  @FXML private
  TableColumn<User, Date> birthdayColumn;
  @FXML private
  TableColumn<User, String> usernameColumn;
  @FXML private
  TableColumn<User, String> passwordColumn;
  @FXML private
  TableColumn<User, String> roleColumn;







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
    firstnameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstname"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastname"));
    birthdayColumn.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
    usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
    passwordColumn.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
    roleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
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

    vm.RemoveUser(username.textProperty().getValue());
     vm.getModelUsers();


  }
 public void  TableViewClicked()
 { User user =null;
    user =RoleTableView.getSelectionModel().getSelectedItem();
   firstname.setText(user.getFirstname());
   lastname.setText(user.getLastname());

   birthdayDatePicker.setValue(user.getBirthday().toLocalDate());
   username.setText(user.getUsername());
   password.setText(user.getPassword());
   roleComboBox.getSelectionModel().select(user.getRole());

 }
}
