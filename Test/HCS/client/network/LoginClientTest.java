package HCS.client.network;

import HCS.DataBase.BookingDAO;
import HCS.DataBase.LoginDAO;
import HCS.DataBase.PatientDAO;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginClientTest
{


  /////////////////////////
  LoginDAO loginDAO;
  PatientDAO patientDAO;
  BookingDAO bookingDAO;
  LoginClientImpl client = new LoginClientImpl();

  @BeforeEach
  public void arrange() {

 loginDAO= LoginDAO.getInstance();
 patientDAO=PatientDAO.getInstance();
 bookingDAO=BookingDAO.getInstance();
  }


  @org.junit.jupiter.api.Test void HCSLogin()
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

    boolean patient= patientDAO.patientExist("11112222");
    assertTrue(patient);

    Date date1= new Date(-1970);
    Booking b= new Booking(date1,"09:30","gsgsggs","11112222");
    bookingDAO.createBooking(b);
     //LocalDate l=new LocalDate(2021,12,03);
    boolean booking =bookingDAO.bookingExist(date1,"09:30");
    assertTrue(booking);
    bookingDAO.removeBooking(date1,"09:30");

  }
}