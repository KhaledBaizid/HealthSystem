package HCS.client.model;

import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface PatientModel extends Subject
{
  void createPatient(Patient patient);
  void removePatient(String cprNumber);
  void updatePatient(String cprNumber,Patient patient);
//  void HCSGetRoles();
  void GetPatients();
  void GetSpecificPatients(String search);

  boolean patientExist(String cprNumber);

 /* void createBooking(Booking booking);
  void HCSGetBookings();
  void removeBooking(Date bookingDate,String bookingTime );
  ArrayList<String> getTimeAvailable(Date date);*/
}
