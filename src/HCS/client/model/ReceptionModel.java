package HCS.client.model;

import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface ReceptionModel extends Subject
{
  void createPatient(Patient patient);
  void HCSGetRoles();
  void HCSGetPatients();
  void HCSGetSpecificPatients(String search);
  void createBooking(Booking booking);
  void HCSGetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getTimeAvailable(Date date);
}