package HCS.client.ViewModel;

import HCS.client.model.BookingModel;
import HCS.client.model.PatientModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class ReceptionViewModel implements Subject
{
  private PropertyChangeSupport support;
  private StringProperty error;
  private PatientModel model;
  private BookingModel bookingModel;
  private ObservableList<User> roles1;
  private ObservableList<Patient> patients;
  private ObservableList<Booking> bookings;

  public ReceptionViewModel(PatientModel model,BookingModel bookingModel)
  {
    this.model=model;
    this.bookingModel=bookingModel;
    support = new PropertyChangeSupport(this);
    roles1= FXCollections.observableArrayList();
    patients=FXCollections.observableArrayList();
    bookings=FXCollections.observableArrayList();
    model.addListener("HCSGetPatients",this::getPatients);
    model.addListener("HCSGetRoles",this::getRoles);
   // model.addListener("HCSGetBookings",this::getBookings);

    bookingModel.addListener("PtientHasBooking",this::patientHasBooking);
    bookingModel.addListener("HCSGetBookings",this::getBookings);
   // model.addListener("HCSGetPatients",this::fireAll);

  }

  private void patientHasBooking(PropertyChangeEvent event)
  {boolean s= (boolean) event.getNewValue();
    System.out.println(s);
   if (s)
   {
     support.firePropertyChange(event);
   }
  }
  public StringProperty errorProperty()
  {
    return error;
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
    ArrayList<User> users =(ArrayList<User>) event.getNewValue();
    roles1.addAll(users);
  }
  public ObservableList<Patient> getTableViewPatients()
  {
    return patients;
  }
  public ObservableList<User> getTableViewRoles()
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
  public void removePatient(String cprNumber)
  { //if (bookingModel.)
    bookingModel.isPatientHasABooking(cprNumber);
    model.removePatient(cprNumber);
  }
  public void updatePatient(String cprNumber,Patient patient)
  {
    model.updatePatient(cprNumber, patient);
  }
  public void getModelPatients()
  {
    model.GetPatients();
  }
  public void getModelSpecificPatients(String search)
  {
    model.GetSpecificPatients(search);
  }
  public void createBooking(Booking booking)
  {
    System.out.println("BookingViewModel");
    bookingModel.createBooking(booking);
  }
  public void getModelBookings()
  {
    bookingModel.GetBookings();
  }
  public ObservableList<Booking> getTableViewBookings()
  {
    return bookings;
  }
  public void removeBooking(Date bookingDate, String bookingTime)
  {
    bookingModel.removeBooking(bookingDate, bookingTime);
  }
  public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingModel.getAvailableTime(date);
  }
  public void getPatientBookings(String cprNumber)
  {
    bookingModel.GetPatientBookings(cprNumber);
  }
  public void GetPatientBookingsByDate(Date date)
  {
    bookingModel.GetPatientBookingsByDate(date);
  }

  public boolean isPatientHasAbooking(String cprNumber)
  {
    return false;
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {

  }
}
