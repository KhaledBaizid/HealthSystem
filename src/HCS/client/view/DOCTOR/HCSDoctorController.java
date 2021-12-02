package HCS.client.view.DOCTOR;

import HCS.client.ViewModel.DoctorViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

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

  @FXML private TextField CPRNumberTextField;
  @FXML private TextField firstnameTextField;
  @FXML private TextField lastnameTextField;


  private ViewHandler vh;
  private DoctorViewModel vm;
  public void init(ViewHandler vh, DoctorViewModel vm)
  {
    this.vh=vh;
    this.vm=vm;

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

  }

  public void bookingTableClicked()
  {
    Booking booking=bookingTableView.getSelectionModel().getSelectedItem();
    CPRNumberTextField.setText(booking.getCprNumber());
    firstnameTextField.setText(booking.getFirstname());
    lastnameTextField.setText(booking.getLastname());


  }

}
