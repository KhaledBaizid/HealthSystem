package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookingModelServerTest
{
  LoginDAO loginDAO;
  AdminDAO adminDAO;
  PatientDAO patientDAO;
  BookingDAO bookingDAO;
  PrescriptionDAO prescriptionDAO;
  Date date= new Date(-1970);

  @BeforeEach
  public void arrange() {

    loginDAO= LoginDAO.getInstance();
    adminDAO=AdminDAO.getInstance();
    patientDAO=PatientDAO.getInstance();
    bookingDAO=BookingDAO.getInstance();
    prescriptionDAO=PrescriptionDAO.getInstance();

  }

  @Test void createBooking()
  {
    String str = "1990-03-31";
    Date date = Date.valueOf(str);
    patientDAO.removePatient("11001122");
    Patient patient = new Patient("11001122", "bob", "sep2", date, "M",
        "anywhere", "12101210", "anymail");
    patientDAO.createPatient(patient);
    Booking booking = new Booking(date, "08:00", "tired", "11001122");
    bookingDAO.createBooking(booking);
    assertTrue(bookingDAO.bookingExist(date, "08:00"));
    bookingDAO.removeBooking(date, "08:00");
    patientDAO.removePatient("11001122");
  }

  @Test void getBookings()
  {
  }

  @Test void removeBooking()
  {
  }

  @Test void updateBooking()
  {
  }

  @Test void getAvailableTime()
  {
  }

  @Test void getPatientBookings()
  {
  }

  @Test void getPatientBookingsByDate()
  {
  }

  @Test void isPatientHasABooking()
  {
  }

  @Test void isBookingHasAPrescription()
  {
  }
}