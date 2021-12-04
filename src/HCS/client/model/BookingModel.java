package HCS.client.model;

import HCS.shared.transferObjects.Booking;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface BookingModel extends Subject
{
  void createBooking(Booking booking);
  void GetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getAvailableTime(Date date);

  void GetPatientBookings(String cprNumber);
  void isPatientHasABooking(String cprNumber);
}
