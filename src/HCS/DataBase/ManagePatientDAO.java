package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.User;

import java.util.ArrayList;

public interface ManagePatientDAO
{
  void createPatient(Patient patient);
 // ArrayList<User> HCSGetRoles();
  void removePatient(String cprNumber);

  void updatePatient(String cprNumber,Patient patient);
  ArrayList<Patient> GetPatients();
  ArrayList<Patient> GetSpecificPatients(String search);

  boolean patientExist(String cprNumber);


 /* void createBooking(Booking booking);
  ArrayList<Booking> HCSGetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getTimeAvailable(Date date);*/
}
