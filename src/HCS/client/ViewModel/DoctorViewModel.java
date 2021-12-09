package HCS.client.ViewModel;

import HCS.client.model.BookingModel;
import HCS.client.model.PrescriptionModel;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Prescription;
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
  private  String radio;
 private  Date specificDate;

  public DoctorViewModel(PrescriptionModel prescriptionModel, BookingModel bookingModel)
  {
    this.prescriptionModel=prescriptionModel;
    this.bookingModel=bookingModel;
    bookings= FXCollections.observableArrayList();
    prescriptions=FXCollections.observableArrayList();
    radio="all";
    specificDate=null;
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


   // if (getRadio().equals("all"))
    //{
      bookings.clear();
      bookings.addAll((ArrayList<Booking>) event.getNewValue());
  /*  }
     else{
      bookings.clear();
      ArrayList<Booking> bookingsradio = (ArrayList<Booking>) event.getNewValue();
    for (int i=0;i<bookingsradio.size();i++)
    {
      if (!bookingsradio.get(i).getBookingDate().toString().equals(getSpecificDate().toString()))
      {
        System.out.println(bookingsradio.get(i).getBookingDate());
        bookingsradio.remove(bookingsradio.get(i));


      }
    }
    bookings.addAll(bookingsradio);
  }*/
  }

  public void getModelBookings()
  {
   // model.HCSGetBookings();
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
  // radio=s;
  // specificDate=date;
    System.out.println(radio+" "+date);
  }

  public void setRadio(String radio)
  {
    this.radio = radio;
  }

  public void setSpecificDate(Date specificDate)
  {
    this.specificDate = specificDate;

  }

  private String getRadio()
  {
    return radio;
  }

  private Date getSpecificDate()
  {
    return specificDate;
  }
}
