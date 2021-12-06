package HCS.client.view.DOCTOR;

import HCS.client.ViewModel.DoctorViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;

public class HCSDoctorController
{
  @FXML private TableView<Booking> bookingTableView;
  @FXML private TableColumn<Booking, Date> bookingDateColumn;
  @FXML private TableColumn<Booking, String> bookingTimeColumn;
  @FXML private TableColumn<Booking, String> bookingCPRNumberColumn;
  @FXML private TableColumn<Booking, String> bookingFirstnameColumn;
  @FXML private TableColumn<Booking, String> bookingLastnameColumn;
  @FXML private TableColumn<Booking, Date> bookingBirthdayColumn;
  @FXML private TableColumn<Booking, String> bookingSexColumn;
  @FXML private TableColumn<Booking, String> bookingSymptomsColumn;

  @FXML private DatePicker bookingDatePicker;
  @FXML private TextField bookingTime;
  @FXML private TextField CPRNumberTextField;
  @FXML private TextField firstnameTextField;
  @FXML private TextField lastnameTextField;
  @FXML private ComboBox prescriptionComboBox;
  @FXML private TextArea prescreptionTextArea;

  @FXML private TableView<Prescription> PrescriptionTableView;
  @FXML private TableColumn<Prescription, Date> prescriptionDateColumn;
  @FXML private TableColumn<Prescription, String> prescriptionTimeColumn;
  @FXML private TableColumn<Prescription, String> prescriptionCprNumberColumn;
  @FXML private TableColumn<Prescription, String> prescriptionFirstnameColumn;
  @FXML private TableColumn<Prescription, String> prescriptionLastnameColumn;
  @FXML private TableColumn<Prescription, Date> prescriptionBirthdayColumn;
  @FXML private TableColumn<Prescription, String> prescriptionSexColumn;
  @FXML private TableColumn<Prescription, String> prescriptionSymptomsColumn;
  @FXML private TableColumn<Prescription, String> prescriptionTypeColumn;
  @FXML private TableColumn<Prescription, String> prescriptionTextColumn;

  @FXML private DatePicker bookingDatePicker1;
  @FXML private TextField bookingTime1;
  @FXML private TextField CPRNumberTextField1;
  @FXML private TextField firstnameTextField1;
  @FXML private TextField lastnameTextField1;
  @FXML private ComboBox prescriptionComboBox1;
  @FXML private TextArea prescreptionTextArea1;

  ////radiobutton
  @FXML private RadioButton allBookingsRadioButton;
  @FXML private RadioButton specificBookingRadioButton;
  @FXML private DatePicker specificDatePicker;
  ////



  private ViewHandler vh;
  private DoctorViewModel vm;
  public void init(ViewHandler vh, DoctorViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;

    prescriptionComboBox.getItems().addAll("Medicines","Scan","BloodTest");
    prescriptionComboBox1.getItems().addAll("Medicines","Scan","BloodTest");

    ToggleGroup radioGroup = new ToggleGroup();
    allBookingsRadioButton.setToggleGroup(radioGroup);
    specificBookingRadioButton.setToggleGroup(radioGroup);
    allBookingsRadioButton.setSelected(true);
    specificDatePicker.setValue(LocalDate.now());

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

    prescriptionDateColumn.setCellValueFactory(new PropertyValueFactory<Prescription,Date>("bookingDate"));
    prescriptionTimeColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("bookingTime"));
    prescriptionCprNumberColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("cprNumber"));
    prescriptionFirstnameColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("firstname"));
    prescriptionLastnameColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("lastname"));
    prescriptionBirthdayColumn.setCellValueFactory(new PropertyValueFactory<Prescription,Date>("birthday"));
    prescriptionSexColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("sex"));
    prescriptionSymptomsColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("symptoms"));
    prescriptionTypeColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("prescriptionType"));
    prescriptionTextColumn.setCellValueFactory(new PropertyValueFactory<Prescription,String>("prescriptionText"));
    vm.getPrescriptionsModel();
   PrescriptionTableView.setItems(vm.getPrescriptionsTableView());
   /////////


    ///////////




  }

  public void onCreatePrescription()
  {
   // String s=prescreptionTextArea.textProperty().getValue();
    //System.out.println(s);
  /* String  s="sgsgsg"+"\n"
       +"sgsgdsgd"
        + "sgsgsggd"
        + "sgsgsggs";
    prescreptionTextArea.setText(s)*/;

    Prescription prescription=new Prescription(Date.valueOf(bookingDatePicker.getValue()),bookingTime.textProperty().getValue(),
        prescriptionComboBox.getSelectionModel().getSelectedItem().toString(),prescreptionTextArea.textProperty().getValue());
    vm.createPrescription(prescription);
    vm.getPrescriptionsModel();
  }

  public void bookingTableClicked()
  {
    Booking booking=bookingTableView.getSelectionModel().getSelectedItem();
    CPRNumberTextField.setText(booking.getCprNumber());
    firstnameTextField.setText(booking.getFirstname());
    lastnameTextField.setText(booking.getLastname());
    bookingDatePicker.setValue(booking.getBookingDate().toLocalDate());
    bookingTime.setText(booking.getBookingTime());


  }

  public void prescriptionTableClicked()
  {
    Prescription prescription= PrescriptionTableView.getSelectionModel().getSelectedItem();
    bookingDatePicker1.setValue(prescription.getBookingDate().toLocalDate());
    bookingTime1.setText(prescription.getBookingTime());
    CPRNumberTextField1.setText(prescription.getFirstname());
    firstnameTextField1.setText(prescription.getFirstname());
    lastnameTextField1.setText(prescription.getLastname());
    prescriptionComboBox1.getSelectionModel().select(prescription.getPrescriptionType());
    prescreptionTextArea1.setText(prescription.getPrescriptionText());

  }

  public void onAllBookingsRadioButton()
  {
   // System.out.println("radiobuttonAll");
    vm.RadioButtonClicked("all",null);
  }
  public void onSpecificBookingsRadioButton()
  {
  //  System.out.println("radiobuttonSpecific");
    vm.RadioButtonClicked("specific",Date.valueOf(specificDatePicker.getValue()));
  }


}
