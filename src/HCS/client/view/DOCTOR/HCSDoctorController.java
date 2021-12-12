package HCS.client.view.DOCTOR;

import HCS.client.ViewModel.DoctorViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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
 // @FXML private ComboBox prescriptionComboBox;
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
  @FXML private TextField  searchByCprNumber;
  @FXML private DatePicker prescriptionSearchByDate;

  String oldPrescriptionType="";

  private ViewHandler vh;
  private DoctorViewModel vm;
  public void init(ViewHandler vh, DoctorViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;

   // prescriptionComboBox.getItems().addAll("Medicines","Scan","BloodTest");
   // prescriptionComboBox1.getItems().addAll("Medicines","Scan","BloodTest");


    prescriptionComboBox.getItems().addAll(vm.getPrescriptionsTypeModel());
    prescriptionComboBox1.getItems().addAll(vm.getPrescriptionsTypeModel());


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
    CPRNumberTextField1.setText(prescription.getCprNumber());
    firstnameTextField1.setText(prescription.getFirstname());
    lastnameTextField1.setText(prescription.getLastname());
    prescriptionComboBox1.getSelectionModel().select(prescription.getPrescriptionType());
    prescreptionTextArea1.setText(prescription.getPrescriptionText());

    oldPrescriptionType=prescription.getPrescriptionType();

  }

  public void onAllBookingsRadioButton()
  {
   // System.out.println("radiobuttonAll");
    vm.RadioButtonClicked("all",null);
    vm.getModelBookings();
  }
  public void onSpecificBookingsRadioButton()
  {
  //  System.out.println("radiobuttonSpecific");
    vm.RadioButtonClicked("specific",Date.valueOf(specificDatePicker.getValue()));
  }

  public void removePrescription()
  {
    Prescription prescription=new Prescription(Date.valueOf(bookingDatePicker1.getValue()),bookingTime1.textProperty().getValue(),
        prescriptionComboBox1.getSelectionModel().getSelectedItem().toString(),
      prescreptionTextArea1.textProperty().getValue());
    vm.removePrescription(prescription);
    //vm.removePrescription();
  }

  public void updatePrescription()
  {
    vm.updatePrescription(Date.valueOf(bookingDatePicker1.getValue()),bookingTime1.textProperty().getValue(),oldPrescriptionType,
        prescriptionComboBox1.getSelectionModel().getSelectedItem().toString(),prescreptionTextArea1.textProperty().getValue());
  }

  public void onBookingSearchDatePicker()
  {
    vm.GetPatientBookingsByDate(Date.valueOf(specificDatePicker.getValue()));
  }

  public void getPrescriptionsByCptNumber()
  {
   // if (!searchByCprNumber.getText().isBlank())
    vm.getPrescriptionsByPatient(searchByCprNumber.textProperty().getValue());
    //else
     // vm.getPrescriptionsModel();
  }

  public void onPrescriptionDatePickerClicked()
  {
    vm.getPrescriptionsByDate(Date.valueOf(prescriptionSearchByDate.getValue()));

  }


}
