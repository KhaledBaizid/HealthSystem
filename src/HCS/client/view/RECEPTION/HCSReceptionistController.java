package HCS.client.view.RECEPTION;

import HCS.client.ViewModel.BookingViewModel;
import HCS.client.ViewModel.ReceptionViewModel;
import HCS.client.core.ViewHandler;
import HCS.client.view.Booking.HCSBookingController;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class HCSReceptionistController
{
  //Patient
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
  private TableView<Patient> patientTableView;
  @FXML private TableColumn<Patient, String> cprNumberColumn;
  @FXML private TableColumn<Patient, String> firstnameColumn;
  @FXML private TableColumn<Patient, String> lastnameColumn;
  @FXML private TableColumn<Patient, Date> birthdayColumn;
  @FXML private TableColumn<Patient, String> sexColumn;
  @FXML private TableColumn<Patient, String> addressColumn;
  @FXML private TableColumn<Patient, String> phoneColumn;
  @FXML private TableColumn<Patient, String> mailColumn;
  //////////////
  ///BOOKING

  @FXML private DatePicker bookingDatePicker;
  @FXML private ComboBox bookingTimeComboBox;
  @FXML private TextField bookingCPRNumber;
  @FXML private TextField bookingFirstname;
  @FXML private TextField bookingLastname;
  @FXML private TextField symptoms;

  @FXML private TableView<Booking> bookingTableView;
  @FXML private TableColumn<Booking, Date> bookingDateColumn;
  @FXML private TableColumn<Booking, String> bookingTimeColumn;
  @FXML private TableColumn<Booking, String> bookingCPRNumberColumn;
  @FXML private TableColumn<Booking, String> bookingFirstnameColumn;
  @FXML private TableColumn<Booking, String> bookingLastnameColumn;
  @FXML private TableColumn<Booking, Date> bookingBirthdayColumn;
  @FXML private TableColumn<Booking, String> bookingSexColumn;
  @FXML private TableColumn<Booking, String> bookingSymptomsColumn;

  @FXML private DatePicker bookingDatePicker1;
  @FXML private ComboBox bookingTimeComboBox1;
  @FXML private TextField bookingCPRNumber1;
  @FXML private TextField bookingFirstname1;
  @FXML private TextField bookingLastname1;
  @FXML private TextField symptoms1;


  /////////////////////
  @FXML
  private TableView<Role> RoleTableView;

  @FXML private TableColumn<Role, String> firstname1Column;
  @FXML private
  TableColumn<Role, String> lastname1Column;
  @FXML private
  TableColumn<Role, Date> birthday1Column;
  @FXML private
  TableColumn<Role, String> usernameColumn;
  @FXML private
  TableColumn<Role, String> passwordColumn;
  @FXML private
  TableColumn<Role, String> roleColumn;
  //////////////
  @FXML
  private TextField searchTextField;
  //////////


  private ViewHandler vh;
  private ReceptionViewModel vm;
  private BookingViewModel vmb;
  private Object HCSBookingController;

  public void init(ViewHandler vh, ReceptionViewModel vm ,BookingViewModel vmb)
  {
    this.vh=vh;
    this.vm=vm;
    this.vmb=vmb;

    sexComboBox.getItems().addAll("F","M");
    bookingDatePicker.setValue(LocalDate.now());
    bookingTimeComboBox.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");
    bookingTimeComboBox1.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");

    //Patient
    cprNumberColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("cprNumber"));
    firstnameColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("firstname"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("lastname"));
    birthdayColumn.setCellValueFactory(new PropertyValueFactory<Patient,Date>("birthday"));
    sexColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("address"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
    mailColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("mail"));
    vm.getModelPatients();
    patientTableView.setItems(vm.getTableViewPatients());

    //BOOKING
    bookingDateColumn.setCellValueFactory(new PropertyValueFactory<Booking,Date>("bookingDate"));
    bookingTimeColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("bookingTime"));
    bookingCPRNumberColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("cprNumber"));
    bookingFirstnameColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("firstname"));
    bookingLastnameColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("lastname"));
    bookingBirthdayColumn.setCellValueFactory(new PropertyValueFactory<Booking,Date>("birthday"));
    bookingSexColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("sex"));
    bookingSymptomsColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("symptoms"));
    vmb.getModelBookings();
    bookingTableView.setItems(vm.getTableViewBookings());

    // vm.addListener("HCSLogin",this::succesfulLogin);
    System.out.println("ReceptionController");
    firstname1Column.setCellValueFactory(new PropertyValueFactory<Role,String>("firstname"));
    lastname1Column.setCellValueFactory(new PropertyValueFactory<Role,String>("lastname"));
    birthday1Column.setCellValueFactory(new PropertyValueFactory<Role,Date>("birthday"));
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
  public void onSearchTyped()
  {
    if (!searchTextField.getText().isBlank())
   vm.getModelSpecificPatients(searchTextField.textProperty().getValue());
    else vm.getModelPatients();
  }

  public void patientTableClicked()
  {

    Patient patient= patientTableView.getSelectionModel().getSelectedItem();
    bookingCPRNumber.setText(patient.getCprNumber());
    bookingFirstname.setText(patient.getFirstname());
    bookingLastname.setText(patient.getLastname());

  }

  public void createBooking()
  { LocalDate localDate=bookingDatePicker.getValue();
    Date date1=Date.valueOf(localDate);
    Booking booking = new Booking(date1,bookingTimeComboBox.getSelectionModel().getSelectedItem().toString(),symptoms.textProperty().getValue(),
        bookingCPRNumber.textProperty().getValue());
    vmb.createBooking(booking);
    System.out.println("BookingController");
  }
  public void removeBooking()
  { LocalDate localDate=bookingDatePicker1.getValue();
    Date date1=Date.valueOf(localDate);
   vmb.removeBooking(date1,bookingTimeComboBox1.getSelectionModel().getSelectedItem().toString());
   vmb.getModelBookings();
  }
  public void bookingTableClicked()
  {

    Booking booking=bookingTableView.getSelectionModel().getSelectedItem();
    bookingDatePicker1.setValue(booking.getBookingDate().toLocalDate());
    bookingCPRNumber1.setText(booking.getCprNumber());
    bookingFirstname1.setText(booking.getFirstname());
    bookingLastname1.setText(booking.getLastname());
    symptoms1.setText(booking.getSymptoms());
    bookingTimeComboBox1.getSelectionModel().select(booking.getBookingTime());
  }
  public void bookingDateClicked()
  {
    LocalDate localDate=bookingDatePicker.getValue();
    Date date2=Date.valueOf(localDate);
    System.out.println(bookingDatePicker.getValue().toString());
    ArrayList<String> time=vm.getTimeAvailable(date2);
   // vm.getTimeAvailable(date2);
    bookingTimeComboBox.getItems().clear();
    bookingTimeComboBox.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");
    bookingTimeComboBox.getItems().removeAll(time);
    for (String i:time)
    {
      System.out.println(i);
    }
    System.out.println("datetime");

  }

  public void BookForPatient()
  {
    //HCSBookingController.class.getClassLoader().
    vh.openHCSBooking(bookingCPRNumber.textProperty().getValue(),bookingFirstname.textProperty().getValue(),bookingLastname.textProperty().getValue());
    //vh.openHCSDoctor("sgsgsgs");
  }
  public void OpenBookPage()
  {
    vh.openHCSBooking();
  }
}
