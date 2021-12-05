package HCS.client.ViewModel;

import HCS.client.model.DoctorModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class DoctorViewModel
{
  private DoctorModel model;
  private ObservableList<Booking> bookings;
  private ObservableList<Prescription> prescriptions;

  public DoctorViewModel(DoctorModel model)
  {
    this.model=model;
    bookings= FXCollections.observableArrayList();
    prescriptions=FXCollections.observableArrayList();

    model.addListener("HCSGetBookings",this::getBookings);
    model.addListener("HCSGetPrescriptions",this::getPrescriptions);

  }

  private void getPrescriptions(PropertyChangeEvent event)
  {
    prescriptions.clear();
    prescriptions.addAll((ArrayList<Prescription>)event.getNewValue());
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
  public ObservableList<Prescription> getPrescriptionsTableView()
  {
    return prescriptions;
  }
  public void getPrescriptionsModel()
  {
    model.getPrescriptions();
  }


  public void createPrescription(Prescription prescription){
    model.createPrescription(prescription);
  }


}
