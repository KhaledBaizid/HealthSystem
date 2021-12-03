package HCS.client.ViewModel;

import HCS.client.model.BookingModel;
import HCS.client.model.DoctorModel;
import HCS.client.model.ReceptionModel;
import HCS.shared.transferObjects.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.util.ArrayList;

public class BookingViewModel
{
  private BookingModel model;
  private ObservableList<Booking> bookings;
  public BookingViewModel(BookingModel model)
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
  public void createBooking(Booking booking)
  {
    System.out.println("BookingViewModel");
    model.createBooking(booking);
  }
  public void getModelBookings()
  {
    model.HCSGetBookings();
  }
  public ObservableList<Booking> getTableViewBookings()
  {
    return bookings;
  }
  public void removeBooking(Date bookingDate, String bookingTime)
  {
    model.removeBooking(bookingDate, bookingTime);
  }
  public ArrayList<String> getTimeAvailable(Date date)
  {
    return model.getTimeAvailable(date);
  }

}
