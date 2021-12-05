package HCS.client.network;

import HCS.DataBase.BookingDAO;
import HCS.DataBase.LoginDAO;
import HCS.DataBase.PatientDAO;
import HCS.DataBase.PrescriptionDAO;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import org.junit.jupiter.api.BeforeEach;
import org.w3c.dom.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginClientTest
{


  /////////////////////////
  LoginDAO loginDAO;
  PatientDAO patientDAO;
  BookingDAO bookingDAO;
  PrescriptionDAO prescriptionDAO;
  LoginClientImpl client = new LoginClientImpl();

  @BeforeEach
  public void arrange() {

 loginDAO= LoginDAO.getInstance();
 patientDAO=PatientDAO.getInstance();
 bookingDAO=BookingDAO.getInstance();
 prescriptionDAO=PrescriptionDAO.getInstance();
  }


  @org.junit.jupiter.api.Test void Login()
  {
   //loginDAO.HCSLogin("b","a");
    String role=loginDAO.Login("a","a");
    assertTrue(role.equals("ADMIN"));
    role=loginDAO.Login("r","r");
    assertTrue(role.equals("RECEPTION"));
    role=loginDAO.Login("d","d");
    assertTrue(role.equals("DOCTOR"));
    role=loginDAO.Login("b","a");
    assertTrue(role==null);

    /*boolean patient= patientDAO.patientExist("11112222");
    assertTrue(patient);*/

    Date date1= new Date(-1970);
    Booking b= new Booking(date1,"09:30","gsgsggs","11110000");
    bookingDAO.createBooking(b);
     //LocalDate l=new LocalDate(2021,12,03);
    boolean booking =bookingDAO.bookingExist(date1,"09:30");
    assertTrue(booking);
    bookingDAO.removeBooking(date1,"09:30");

    boolean find=false;
    find=bookingDAO.isPatientHasABooking("11110000");
    assertTrue(find);

    Patient p= new Patient("11110000","khaled","baizid",date1,"M","adress","12345621","mail");
    patientDAO.updatePatient("11110000",p);
    patientDAO.patientExist("11110000");

    ArrayList<Booking> bookings= bookingDAO.GetPatientBookings("11113333");
    assertTrue(bookings.get(0).getBookingTime().equals("08:30") );
    for(int i=0 ;i<bookings.size();i++)
    {
      System.out.println(bookings.get(i).getBookingTime());
    }

   // Text prescription="you" +"\n"+"should"+"\n"+"relax";


   /* String prescriptionText="hi"+"\n"+"i am here"+"\n"+"what about you";
    Prescription prescription=new Prescription(date1,"08:00","Scan",prescriptionText);
    prescriptionDAO.createPrescription(prescription);*/

    String s=prescriptionDAO.getSpecificPrescription(date1,"08:00","Scan");
    System.out.println(s);

  }
}