package HCS.client.ViewModel;

import HCS.client.model.BookingModel;
import HCS.client.model.PrescriptionModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.util.ArrayList;

public class DoctorViewModel
{
  private PrescriptionModel prescriptionModel;
  private BookingModel bookingModel;
  private ObservableList<Booking> bookings;
  private ObservableList<Prescription> prescriptions;

  private ObservableList<String> prescriptionsType;

    String radio;
   Date specificDate;
  ArrayList<Booking> bookingsradio;
  ArrayList<Booking> bookingsradio1;

  public DoctorViewModel(PrescriptionModel prescriptionModel, BookingModel bookingModel)
  {
    this.prescriptionModel=prescriptionModel;
    this.bookingModel=bookingModel;
    bookings= FXCollections.observableArrayList();
    prescriptions=FXCollections.observableArrayList();
    prescriptionsType=FXCollections.observableArrayList();
    radio="all";
    specificDate=null;
    bookingsradio=new ArrayList<>();
    bookingsradio1=new ArrayList<>();
    bookingModel.addListener("HCSGetBookings",this::getBookings);
    prescriptionModel.addListener("HCSGetPrescriptions",this::getPrescriptions);

  }

  private void getPrescriptions(PropertyChangeEvent event)
  {
    prescriptions.clear();
    prescriptions.addAll((ArrayList<Prescription>)event.getNewValue());
  }

  private void getBookings(PropertyChangeEvent event)
  {
    if (radio.equals("all"))
    {
      bookings.clear();
      bookings.addAll((ArrayList<Booking>) event.getNewValue());
      System.out.println("ALLLLLL");
    }
     else if (radio.equals("specific")){


      bookingsradio1.clear();
      bookingsradio.clear();
     bookingsradio.addAll((ArrayList<Booking>) event.getNewValue());
      for (Booking booking : bookingsradio)
      {
        if (booking.getBookingDate().toString()
            .equals(specificDate.toString()))
        {
          bookingsradio1.add(booking);

        }

      }
      bookings.clear();
      bookings.addAll( bookingsradio1);

  } else {}
  }



  public void getModelBookings()
  {

    bookingModel.GetBookings();
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
    prescriptionModel.getPrescriptions();
  }


  public void createPrescription(Prescription prescription){
    prescriptionModel.createPrescription(prescription);
  }

  public void RadioButtonClicked(String s, Date date)
  {
    this.setRadio(s);
    this.setSpecificDate(date);

   radio=s;

  }

  public void setRadio(String radio)
  {
    this.radio = radio;
  }

  public void setSpecificDate(Date specificDate)
  {
    this.specificDate = specificDate;

  }



  public void removePrescription(Prescription prescription)
  {
    prescriptionModel.removePrescription(prescription);
  }

  public void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText)
  {
    prescriptionModel.updatePrescription(bookingDate, bookingTime, prescriptionType, newPrescriptionType, prescriptionText);
  }
  public void GetBookingsByDate(Date date)
  {
    specificDate=date;
    bookingModel.GetBookingsByDate(date);

  }

  public void   getPrescriptionsByPatient(String cprNumber)
  {
   prescriptionModel.getPrescriptionsByPatient(cprNumber);

  }

  public void getPrescriptionsByDate(Date date)
  {
    prescriptionModel.getPrescriptionsByDate(date);
  }

  public   void getPrescriptionsType()
  {
    prescriptionsType.clear();
      prescriptionsType.addAll(prescriptionModel.getPrescriptionsType());
  }

  public ObservableList<String> getPrescriptionsTypeList()
  {
    return prescriptionsType;
  }

  public   ArrayList<String> getPrescriptionsTypeModel()
  {

   return prescriptionModel.getPrescriptionsType();
  }

  public void Disconnect()
  {
    bookingModel.Disconnect();
  }

}
