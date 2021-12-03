package HCS.DataBase;

import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;

import java.sql.Date;
import java.util.ArrayList;

public interface ManagePatientDAO
{
  void createPatient(Patient patient);
  ArrayList<Role> HCSGetRoles();
  ArrayList<Patient> HCSGetPatients();
  ArrayList<Patient> HCSGetSpecificPatients(String search);

  boolean patientExist(String cprNumber);

 /* void createBooking(Booking booking);
  ArrayList<Booking> HCSGetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getTimeAvailable(Date date);*/
}
