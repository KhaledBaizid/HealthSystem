package HCS.client.ViewModel;

import HCS.client.model.ReceptionModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.util.ArrayList;

public class ReceptionViewModel
{
  private ReceptionModel model;
  private ObservableList<Role> roles1;
  private ObservableList<Patient> patients;
  private ObservableList<Booking> bookings;

  public ReceptionViewModel(ReceptionModel model)
  {
    this.model=model;
    roles1= FXCollections.observableArrayList();
    patients=FXCollections.observableArrayList();
    bookings=FXCollections.observableArrayList();
    model.addListener("HCSGetPatients",this::getPatients);
    model.addListener("HCSGetRoles",this::getRoles);
    model.addListener("HCSGetBookings",this::getBookings);
  }

  private void getBookings(PropertyChangeEvent event)
  {
    bookings.clear();
    bookings.addAll((ArrayList<Booking>)event.getNewValue());
  }

  private void getPatients(PropertyChangeEvent event)
  {
    patients.clear();
    ArrayList<Patient> patients1=(ArrayList<Patient> )event.getNewValue();
    patients.addAll(patients1);
  }

  private void getRoles(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<Role> roles=(ArrayList<Role>) event.getNewValue();
    roles1.addAll(roles);
  }
  public ObservableList<Patient> getTableViewPatients()
  {
    return patients;
  }
  public ObservableList<Role> getTableViewRoles()
  {

    return roles1;
  }
  public void getModelRoles()
  {
    System.out.println("viewmodel");
    model.HCSGetRoles();
  }

  public void createPatient(Patient patient)
  {
    System.out.println("ReceptionViewModel");
    model.createPatient(patient);

  }
  public void getModelPatients()
  {
    model.HCSGetPatients();
  }
  public void getModelSpecificPatients(String search)
  {
    model.HCSGetSpecificPatients(search);
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
