package HCS.client.view.RECEPTION;

//import HCS.client.ViewModel.BookingViewModel;
import HCS.client.ViewModel.ReceptionViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
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

  //////EDIT PATIENT///
  @FXML
  private TextField cprNumber1;
  @FXML
  private TextField firstname1;
  @FXML
  private TextField lastname1;
  @FXML
  private DatePicker birthdayDatePicker1;
  @FXML
  private ComboBox sexComboBox1;
  @FXML
  private TextField address1;
  @FXML
  private TextField phone1;
  @FXML
  private TextField mail1;
  //////////////

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

  @FXML private TextField bookingSearch;

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

  @FXML private DatePicker bookingSearchDatePicker;

  /////////////////////

  //////////////
  @FXML
  private TextField searchTextField;
  //////////


  private ViewHandler vh;
  private ReceptionViewModel vm;


  Date dateToBeChanged=null;
  String timeToBeChanged="";
  String UpdatedcprNumber="";

  public void init(ViewHandler vh, ReceptionViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;
    //this.vmb=vmb;

    sexComboBox.getItems().addAll("F","M");
    sexComboBox1.getItems().addAll("F","M");
    sexComboBox.getSelectionModel().select("M");
    bookingDatePicker.setValue(LocalDate.now());
    bookingTimeComboBox.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");
    bookingTimeComboBox1.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");

    //bookingTimeComboBox.getSelectionModel().selectFirst();
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
    vm.getModelBookings();
    bookingTableView.setItems(vm.getTableViewBookings());

    vm.addListener("PtientHasBooking",this::removingPatientError);
    vm.addListener("PatientExists",this::patientExists);
    vm.addListener("BookingHasPrescription",this::removeBookingError);

    vm.addListener("updatedPtientHasAUsedCPRNumber",this::updatePatientError);
    vm.addListener("EditFields",this::editFields);
    vm.addListener("chooseTime",this::chooseABookingTime);
    vm.addListener("choosePatient",this::chooseAPatientForBooking);


    cprNumber.textProperty().bindBidirectional(vm.getCprNumber());
    firstname.textProperty().bindBidirectional(vm.getFirstname());
    lastname.textProperty().bindBidirectional(vm.getLastname());
    birthdayDatePicker.valueProperty().bindBidirectional(vm.getBithday());
    sexComboBox.valueProperty().bindBidirectional(vm.getSex());
    address.textProperty().bindBidirectional(vm.getAddress());
    phone.textProperty().bindBidirectional(vm.getPhone());
    mail.textProperty().bindBidirectional(vm.getMail());

    birthdayDatePicker.valueProperty().unbind();
    birthdayDatePicker.setValue(LocalDate.now());
    sexComboBox.valueProperty().unbind();
    sexComboBox.getSelectionModel().selectFirst();


  }

  private void chooseAPatientForBooking(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "Select a patient from the List of patients");
        alert.showAndWait();

      });
    }
  }

  private void chooseABookingTime(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "Choose a booking Time");
        alert.showAndWait();

      });
    }
  }

  private void editFields(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "cprNumber, firstname , lastname can not be empty");
        alert.showAndWait();

      });
    }
  }

  private void updatePatientError(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "You can not update The new patient with an other used CPR Number ");
        alert.showAndWait();

      });
    }

  }

  private void removeBookingError(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();

    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "You can not remove The Booking because it has a Prescription ");
        alert.showAndWait();

      });
    }
  }

  private void patientExists(PropertyChangeEvent event)
  {
    boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "The CPR Number is already used");
        alert.showAndWait();

      });
    }

  }

  private void removingPatientError(PropertyChangeEvent event)
  { boolean s= (boolean) event.getNewValue();
    System.out.println(s);
    if (s)
    {
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(
            "The Patient can not be deleted because he has a booking time");
        alert.showAndWait();

      });

    }


  }

  public void createPatient()
  {

    vm.createPatient();

  }

  public void updatePatient()
  {
    LocalDate localDate=birthdayDatePicker1.getValue();
    Date date=Date.valueOf(localDate);
    Patient patient=new Patient(cprNumber1.textProperty().getValue(),firstname1.textProperty().getValue(),lastname1.textProperty().getValue(),date,
        sexComboBox1.getSelectionModel().getSelectedItem().toString(),address1.textProperty().getValue(),phone1.textProperty().getValue(),mail1.textProperty().getValue());
    vm.updatePatient(UpdatedcprNumber,patient);
  }

  public void removePatient()
  {
   // if (vm.is)
  vm.removePatient(cprNumber1.textProperty().getValue());
  }


  public void onSearchTyped()
  {
  //  if (!searchTextField.getText().isEmpty())
   vm.getModelSpecificPatients(searchTextField.textProperty().getValue());
   // else vm.getModelPatients();
  }
  public void onCPRNumberTyped()
  {
  //  if (!cprNumber.getText().isEmpty())
    vm.getSpecificPatientByCpr();
     // vm.getModelSpecificPatients(cprNumber.textProperty().getValue());
   // else vm.getModelPatients();
  }

  public void patientTableClicked()
  {

    Patient patient= patientTableView.getSelectionModel().getSelectedItem();
    bookingCPRNumber.setText(patient.getCprNumber());
    bookingFirstname.setText(patient.getFirstname());
    bookingLastname.setText(patient.getLastname());

    cprNumber1.setText(patient.getCprNumber());
    firstname1.setText(patient.getFirstname());
    lastname1.setText(patient.getLastname());
    birthdayDatePicker1.setValue(patient.getBirthday().toLocalDate());
    sexComboBox1.getSelectionModel().select(patient.getSex());
    address1.setText(patient.getAddress());
    phone1.setText(patient.getPhone());
    mail1.setText(patient.getMail());

    UpdatedcprNumber=patient.getCprNumber();

  }

  public void createBooking()
  { LocalDate localDate=bookingDatePicker.getValue();
    Date date1=Date.valueOf(localDate);
   /* Booking booking = new Booking(date1,bookingTimeComboBox.getSelectionModel().getSelectedItem().toString(),symptoms.textProperty().getValue(),
        bookingCPRNumber.textProperty().getValue());
    vm.createBooking(booking);*/
    String bookingTime;
    if (bookingTimeComboBox.getSelectionModel().getSelectedItem()==null)
      bookingTime="";
    else
      bookingTime=bookingTimeComboBox.getSelectionModel().getSelectedItem().toString();
    vm.createBooking(date1,bookingTime,symptoms.textProperty().getValue(), bookingCPRNumber.textProperty().getValue());
    System.out.println(bookingTimeComboBox.getSelectionModel().getSelectedItem());
  }
  public void removeBooking()
  { LocalDate localDate=bookingDatePicker1.getValue();
    Date date1=Date.valueOf(localDate);
   vm.removeBooking(date1,bookingTimeComboBox1.getSelectionModel().getSelectedItem().toString());
   vm.getModelBookings();
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

    dateToBeChanged= booking.getBookingDate();
    timeToBeChanged=booking.getBookingTime();

  }
  public void bookingTimeClicked()
  {
    LocalDate localDate=bookingDatePicker.getValue();
    Date date2=Date.valueOf(localDate);
    System.out.println(bookingDatePicker.getValue().toString());
    ArrayList<String> time=vm.getAvailableTime(date2);

    bookingTimeComboBox.getItems().clear();
    bookingTimeComboBox.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");
    bookingTimeComboBox.getItems().removeAll(time);


  }
  public void onBookingTyped()
  {
    vm.GetBookingsBYCprNumber(bookingSearch.textProperty().getValue());
  }

  public void onBookingSearchDatePicker()
  {
    vm.GetBookingsByDate(Date.valueOf(bookingSearchDatePicker.getValue()));
  }

  public void updateBooking()
  {
    LocalDate localDate=bookingDatePicker1.getValue();
    Date date1=Date.valueOf(localDate);
    String newbBookingTime;
    if (bookingTimeComboBox1.getSelectionModel().getSelectedItem()==null)
      newbBookingTime="";
    else
     newbBookingTime=bookingTimeComboBox1.getSelectionModel().getSelectedItem().toString();

 //   Booking booking = new Booking(date1,bookingTimeComboBox1.getSelectionModel().getSelectedItem().toString(),symptoms1.textProperty().getValue(),
     //   bookingCPRNumber1.textProperty().getValue());
  //  vm.updateBooking(dateToBeChanged,timeToBeChanged,booking);
    vm.updateBooking(dateToBeChanged,timeToBeChanged,date1,newbBookingTime,symptoms1.textProperty().getValue(),bookingCPRNumber1.textProperty().getValue());

  }

  public void bookingTime1Clicked()
  {
    LocalDate localDate=bookingDatePicker1.getValue();
    Date date2=Date.valueOf(localDate);
    System.out.println(bookingDatePicker1.getValue().toString());
    ArrayList<String> time=vm.getAvailableTime(date2);
    // vm.getTimeAvailable(date2);
    bookingTimeComboBox1.getItems().clear();
    bookingTimeComboBox1.getItems().addAll("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
        "11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45");
    bookingTimeComboBox1.getItems().removeAll(time);


  }
  public void Disconnect()
  {
   vm.Disconnect();
   System.exit(0);

  }


}
