package HCS.client.view.ADMIN;

import HCS.client.ViewModel.AdminViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
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

  @FXML
  private TextField firstname1;
  @FXML
  private TextField lastname1;

  @FXML
  private TextField username1;
  @FXML
  private TextField password1;
  @FXML
  private DatePicker birthdayDatePicker1;


  @FXML
  private ComboBox roleComboBox1;




  String newUsername="";
  private ViewHandler vh;
  private AdminViewModel vm;
  public void init(ViewHandler vh, AdminViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;
    roleComboBox1.getItems().addAll("RECEPTION","DOCTOR");

    firstname1.textProperty().bindBidirectional(vm.getFirstname());
    lastname1.textProperty().bindBidirectional(vm.getLastname());
    username1.textProperty().bindBidirectional(vm.getUsername());
    password1.textProperty().bindBidirectional(vm.getPassword());
    birthdayDatePicker1.valueProperty().bindBidirectional(vm.getBirthday());
    //vm.getSex().bindBidirectional(sexComboBox.valueProperty());
    roleComboBox1.valueProperty().bindBidirectional(vm.getRole());
    roleComboBox1.valueProperty().unbind();
    roleComboBox1.getSelectionModel().selectFirst();
   // vm.addListener("HCSLogin",this::succesfulLogin);

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

    vm.addListener("usernameExists",this::usernameError);


    ///

  }

  private void usernameError(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();

    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "The username is already taken");
        alert.showAndWait();

      });
    }
  }

  private void getRoles()
  {
    vm.getModelUsers();
  }

  public void createUser()
  {
    /*  LocalDate localDate=birthdayDatePicker1.getValue();
      Date date=Date.valueOf(localDate);
    vm.CreateUser(firstname1.textProperty().getValue(),
                     lastname1.textProperty().getValue(),
        date,
        username1.textProperty().getValue(),
        password1.textProperty().getValue(),
        roleComboBox1.getSelectionModel().getSelectedItem().toString());
    firstname1.clear();lastname1.clear();username1.clear();password1.clear();*/

    vm.CreateUser();

  }

  public void deleteUser()
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
   if (!user.getRole().equals("ADMIN"))
   {roleComboBox.getItems().clear();
     roleComboBox.getItems().addAll("RECEPTION","DOCTOR");

   }
   else
   {roleComboBox.getItems().clear();
     roleComboBox.getItems().addAll("ADMIN");
   }
   roleComboBox.getSelectionModel().select(user.getRole());
   newUsername=user.getUsername();

 }

 public void updateUser()
 {
   User user= new User(firstname.textProperty().getValue(),lastname.textProperty().getValue(),Date.valueOf(birthdayDatePicker.getValue()),username.textProperty().getValue(),
       password.textProperty().getValue(),roleComboBox.getSelectionModel().getSelectedItem().toString());
   vm.updateUser(newUsername,user);

 }
}
