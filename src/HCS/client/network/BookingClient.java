package HCS.client.network;

import HCS.shared.transferObjects.Booking;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface BookingClient extends Subject
{
  void startClient();
  void createBooking(Booking booking);
  void GetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  void updateBooking(Date bookingDate,String bookingTime,Booking booking);
  ArrayList<String> getAvailableTime(Date date);
  void GetBookingsBYCprNumber(String cprNumber);
  void GetBookingsByDate(Date date);
  boolean isPatientHasABooking(String cprNumber);
  boolean isBookingHasAPrescription(Date bookingDate,String bookingTime);

}
