package HCS.server.model;

import HCS.shared.transferObjects.Booking;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface BookingModelServer extends Subject
{
  void createBooking(Booking booking);
  ArrayList<Booking> GetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getAvailableTime(Date date);
  ArrayList<Booking> GetPatientBookings(String cprNumber);
  ArrayList<Booking> GetPatientBookingsByDate(Date date);

  boolean isPatientHasABooking(String cprNumber);
}
