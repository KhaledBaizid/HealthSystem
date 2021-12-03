package HCS.DataBase;

import HCS.shared.transferObjects.Booking;

import java.sql.Date;
import java.util.ArrayList;

public interface ManageBookingDAO
{
  void createBooking(Booking booking);
  ArrayList<Booking> HCSGetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getTimeAvailable(Date date);
  boolean bookingExist(Date bookingDate,String bookingTime );
}
