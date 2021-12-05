package HCS.DataBase;

import HCS.shared.transferObjects.Booking;

import java.sql.Date;
import java.util.ArrayList;

public interface ManageBookingDAO
{
  void createBooking(Booking booking);
  ArrayList<Booking> GetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getAvailableTime(Date date);
  boolean bookingExist(Date bookingDate,String bookingTime );
  boolean isPatientHasABooking(String cprNumber);

  ArrayList<Booking> GetPatientBookings(String cprNumber);
  ArrayList<Booking> GetPatientBookingsByDate(Date date);
}
