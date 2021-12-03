package HCS.client.view.Booking;

import HCS.client.ViewModel.BookingViewModel;
import HCS.client.ViewModel.DoctorViewModel;
import HCS.client.core.ViewHandler;
import HCS.client.view.RECEPTION.HCSReceptionistController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class HCSBookingController
{
  @FXML private DatePicker bookingDatePicker;
  @FXML private ComboBox bookingTimeComboBox;
  @FXML private TextField bookingCPRNumber;
  @FXML private TextField bookingFirstname;
  @FXML private TextField bookingLastname;
  @FXML private TextField symptoms;
  private ViewHandler vh;
  private BookingViewModel vm;

  public void init(ViewHandler vh, BookingViewModel vm,String c,String f,String l)
  {
    this.vh = vh;
    this.vm = vm;
  // t();
    bookingFirstname.setText(f);
    bookingLastname.setText(l);
  }

  public void t()
  {
    try
    {
      bookingCPRNumber.setText(String.valueOf((HCSReceptionistController.class.getDeclaredField(bookingCPRNumber.textProperty().getValue()))));
    }
    catch (NoSuchFieldException e)
    {
      e.printStackTrace();
    }
  }


  public void BackToPatient()
  {
    vh.openHCSReceptionist("reception");

  }
}
