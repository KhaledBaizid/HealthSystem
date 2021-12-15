package HCS.client.ViewModel;

import HCS.client.model.BookingModel;
import HCS.client.model.PatientModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReceptionViewModel implements Subject
{
  private PropertyChangeSupport support;
  private StringProperty error;
  private PatientModel patientmodel;
  private BookingModel bookingModel;
 // private ObservableList<User> roles1;
  private ObservableList<Patient> patients;
  private ObservableList<Booking> bookings;

  private ObservableList<String> sexes;

  private ObjectProperty<LocalDate> birthday;
  private SimpleObjectProperty<String> sex;
  //private StringProperty sex;
  private StringProperty cprNumber;
  private StringProperty firstname;
  private StringProperty lastname;
  private StringProperty address;
  private StringProperty phone;
  private StringProperty mail;




  public ReceptionViewModel(PatientModel patientmodel,BookingModel bookingModel)
  {
    this.patientmodel=patientmodel;
    this.bookingModel=bookingModel;
    support = new PropertyChangeSupport(this);
   // roles1= FXCollections.observableArrayList();
    patients=FXCollections.observableArrayList();
    bookings=FXCollections.observableArrayList();
    sexes=FXCollections.observableArrayList();
    patientmodel.addListener("HCSGetPatients",this::getPatients);
  //  model.addListener("HCSGetRoles",this::getRoles);

    bookingModel.addListener("HCSGetBookings",this::getBookings);
    /////
    birthday=new SimpleObjectProperty<LocalDate>();
    sex= new SimpleObjectProperty<>();
    cprNumber=new SimpleStringProperty();
    firstname=new SimpleStringProperty();
    lastname=new SimpleStringProperty();;
    address=new SimpleStringProperty();
    phone=new SimpleStringProperty();
    mail=new SimpleStringProperty();;


    ////


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

 /* private void getRoles(PropertyChangeEvent event)
  {
    roles1.clear();
    ArrayList<User> users =(ArrayList<User>) event.getNewValue();
    roles1.addAll(users);
  }*/
  public ObservableList<Patient> getTableViewPatients()
  {
    return patients;
  }
 /* public ObservableList<User> getTableViewRoles()
  {

    return roles1;
  }*/

   public boolean isPatientExist(String cprNumber)
   {
     return patientmodel.patientExist(cprNumber);
   }

  public void createPatient()
  {
    System.out.println("ReceptionViewModel");
    Patient patient= new Patient(cprNumber.get(), firstname.get(),
        lastname.get(), Date.valueOf(birthday.get()),sex.get(),address.get(),
        phone.get(), mail.get());
    // sex.bind(getsexes());
  //  if (!model.patientExist(patient.getCprNumber()))
      if (!isPatientExist( patient.getCprNumber()))
      {
        addPatient(patient);
        cprNumber.setValue("");firstname.setValue("");lastname.setValue("");address.setValue("");phone.setValue("");mail.setValue("");
      }
    else
      support.firePropertyChange("PatientExists",null,true);

  }
  public void addPatient(Patient patient)
  {
    patientmodel.createPatient(patient);
  }
  public void removePatient(String cprNumber)
  {

   if(! bookingModel.isPatientHasABooking(cprNumber))
     patientmodel.removePatient(cprNumber);
   else
     support.firePropertyChange("PtientHasBooking",null,true);
  }
  public void updatePatient(String cprNumber,Patient patient)
  {
    if (!cprNumber.equals(patient.getCprNumber()))
    {
      if (isPatientExist( patient.getCprNumber()))
      {
        support.firePropertyChange("updatedPtientHasAUsedCPRNumber",null,true);

      }
      else  patientmodel.updatePatient(cprNumber, patient);
    } else
      patientmodel.updatePatient(cprNumber, patient);
  }
  public void getModelPatients()
  {
    patientmodel.GetPatients();
  }
  public void getModelSpecificPatients(String search)
  {
    patientmodel.GetSpecificPatients(search);
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
    if (!bookingModel.isBookingHasAPrescription(bookingDate, bookingTime))
    bookingModel.removeBooking(bookingDate, bookingTime);
    else
      support.firePropertyChange("BookingHasPrescription",null,true);

  }
  public  void updateBooking(Date bookingDate,String bookingTime,Booking booking)
  {
    bookingModel.updateBooking(bookingDate, bookingTime, booking);
  }
  public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingModel.getAvailableTime(date);
  }
  public void GetBookingsBYCprNumber(String cprNumber)
  {
    bookingModel.GetBookingsBYCprNumber(cprNumber);
  }
  public void GetBookingsByDate(Date date)
  {
    bookingModel.GetBookingsByDate(date);
  }

  public boolean isPatientHasAbooking(String cprNumber)
  {
    return false;
  }


  public void Disconnect()
  {
    patientmodel.Disconnect();
    bookingModel.Disconnect();
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

  //////
  public ObjectProperty<LocalDate> getBithday()
  {
    return birthday;
  }
  public SimpleObjectProperty getSex()
  {
    return sex;
  }

  public StringProperty getAddress()
  {
    return address;
  }

  public StringProperty getCprNumber()
  {
    return cprNumber;
  }

  public StringProperty getFirstname()
  {
    return firstname;
  }

  public StringProperty getLastname()
  {
    return lastname;
  }

  public StringProperty getMail()
  {
    return mail;
  }

  public StringProperty getPhone()
  {
    return phone;
  }

  public void getSpecificPatientByCpr()
  {
    patientmodel.GetSpecificPatients(cprNumber.get());
  }

  public ObservableList<String> getsexes()
  {
    sexes.clear();
    sexes.addAll("a","b","c");
    return sexes;
  }

  public void displayDateBind()
  {
    System.out.println(birthday.toString());
   // LocalDate d= birthday.get();
    System.out.println(Date.valueOf((birthday.get())));
    System.out.println(sex.get());
  }
}
