package HCS.server.network;

import HCS.shared.ClientReceptionCallBack;
import HCS.shared.ClientCallBack;
//import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.User;
//import HCS.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface RMIServer extends Remote
{




 // void disconnect(User userDisconnecting) throws RemoteException;
 //void registerClient();
  void registerClient(ClientCallBack clientCallBack) throws RemoteException;
 void registerClient(ClientCallBack clientCallBack, ClientReceptionCallBack clientReceptionCallBack) throws RemoteException;
   void unregisterClient(ClientCallBack clientCallBack) throws RemoteException;


  String Login(String username,String password) throws RemoteException;

  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role)throws RemoteException;
  ArrayList<User> GetUsers() throws RemoteException;
  void RemoveUser(String username) throws RemoteException;

 boolean patientExist(String cprNumber) throws RemoteException;
  void createPatient(Patient patient) throws RemoteException;
 void removePatient(String cprNumber) throws RemoteException ;
 void updatePatient(String cprNumber,Patient patient) throws RemoteException;
 ArrayList<Patient> GetPatients() throws RemoteException;
 ArrayList<Patient> GetSpecificPatients(String search) throws  RemoteException;

 void createBooking(Booking booking) throws RemoteException;
 ArrayList<Booking> GetBookings() throws RemoteException;
 void removeBooking(Date bookingDate,String bookingTime ) throws RemoteException;
 void updateBooking(Date bookingDate,String bookingTime,Booking booking) throws RemoteException;
 ArrayList<String> getAvailableTime(Date date) throws RemoteException;
 ArrayList<Booking> GetBookingsBYCprNumber(String cprNumber) throws RemoteException;
 ArrayList<Booking> GetPatientBookingsByDate(Date date) throws RemoteException;
 boolean isPatientHasABooking(String cprNumber) throws  RemoteException;


 boolean UserExist(String username) throws RemoteException;

 /////
 void createPrescription(Prescription prescription) throws RemoteException;
 ArrayList<Prescription>  getPrescriptions() throws  RemoteException;
 boolean isBookingHasAPrescription(Date bookingDate,String bookingTime) throws RemoteException;
 void removePrescription(Prescription prescription) throws RemoteException;
 void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText) throws RemoteException;



 /////
}

