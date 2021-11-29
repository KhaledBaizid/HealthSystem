package HCS.client.view.RECEPTION;

import HCS.client.ViewModel.HCSReceptionistViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;

public class HCSReceptionistController
{
  @FXML
  private TextField cprNumber;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private DatePicker birthdayDatePicker;
  @FXML
  private ComboBox sexComboBox;
  @FXML
  private TextField address;
  @FXML
  private TextField phone;
  @FXML
  private TextField mail;


  @FXML
  private TableView<Role> RoleTableView;

  @FXML private TableColumn<Role, String> firstnameColumn;
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
  private HCSReceptionistViewModel vm;
  public void init(ViewHandler vh, HCSReceptionistViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;

    sexComboBox.getItems().addAll("F","M");
    // vm.addListener("HCSLogin",this::succesfulLogin);
    System.out.println("ReceptionController");
    firstnameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("firstname"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("lastname"));
    birthdayColumn.setCellValueFactory(new PropertyValueFactory<Role,Date>("birthday"));
    usernameColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("username"));
    passwordColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("password"));
    roleColumn.setCellValueFactory(new PropertyValueFactory<Role,String>("role"));
    vm.getModelRoles();
    RoleTableView.setItems(vm.getTableViewRoles());
  }

  public void createPatient()
  {
    System.out.println("ReceptionController");
    LocalDate localDate=birthdayDatePicker.getValue();
    Date date=Date.valueOf(localDate);
    Patient patient=new Patient(cprNumber.textProperty().getValue(),firstname.textProperty().getValue(),lastname.textProperty().getValue(),date,
        sexComboBox.getSelectionModel().getSelectedItem().toString(),address.textProperty().getValue(),phone.textProperty().getValue(),mail.textProperty().getValue());
    vm.createPatient(patient);

  }

  public void updatePatient(ActionEvent actionEvent)
  {
  }

  public void removePatient(ActionEvent actionEvent)
  {
  }
}
