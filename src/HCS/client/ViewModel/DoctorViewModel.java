package HCS.client.ViewModel;

import HCS.client.model.DoctorModel;
import HCS.shared.transferObjects.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class DoctorViewModel
{
  private DoctorModel model;
  private ObservableList<Booking> bookings;


  public DoctorViewModel(DoctorModel model)
  {
    this.model=model;
    bookings= FXCollections.observableArrayList();

    model.addListener("HCSGetBookings",this::getBookings);

  }

  private void getBookings(PropertyChangeEvent event)
  {
    bookings.clear();
    bookings.addAll((ArrayList<Booking>)event.getNewValue());
  }

  public void getModelBookings()
  {
    model.HCSGetBookings();
  }
  public ObservableList<Booking> getTableViewBookings()
  {
    return bookings;
  }


}
