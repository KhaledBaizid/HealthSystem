package HCS.Persistence;

import HCS.shared.transferObjects.Booking;

import java.sql.Date;
import java.util.ArrayList;

public interface ManageBookingDAO
{
  void createBooking(Booking booking);
  ArrayList<Booking> GetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  void updateBooking(Date bookingDate,String bookingTime,Booking booking);
  ArrayList<String> getAvailableTime(Date date);
  boolean bookingExist(Date bookingDate,String bookingTime );
  boolean isPatientHasABooking(String cprNumber);

  ArrayList<Booking> GetBookingsBYCprNumber(String cprNumber);
  ArrayList<Booking> GetBookingsByDate(Date date);
}
