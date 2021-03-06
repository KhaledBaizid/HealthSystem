package HCS.server.network;

import HCS.server.model.*;
import HCS.shared.PatientClientCallBack;
import HCS.shared.BookingClientCallBack;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.*;
//import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{

  private final LoginModelServer loginModelServer;
  private final UserModelServer userModelServer;
  private final PatientModelServer patientModelServer;
  private final BookingModelServer bookingModelServer;
  private final PrescriptionModelServer prescriptionModelServer;
  private List<BookingClientCallBack> callTheBookingClient;
  private List<PatientClientCallBack> callThePatientClient;
  
  public RMIServerImpl( LoginModelServer loginModelServer, UserModelServer userModelServer, PatientModelServer patientModelServer,
      BookingModelServer bookingModelServer, PrescriptionModelServer prescriptionModelServer) throws RemoteException
  {
    UnicastRemoteObject.exportObject( this, 0);
    callTheBookingClient =new ArrayList<>();
    callThePatientClient=new ArrayList<>();
    this.loginModelServer = loginModelServer;
    this.userModelServer = userModelServer;
    this.patientModelServer=patientModelServer;
    this.bookingModelServer=bookingModelServer;
    this.prescriptionModelServer=prescriptionModelServer;

    bookingModelServer.addListener("HCSGetBookings",this::sharedBookings);
    patientModelServer.addListener("HCSGetBookings",this::sharedBookings);

    patientModelServer.addListener("HCSGetPatients",this::sharedPatients);

  }

  private void sharedPatients(PropertyChangeEvent event)
  {
    for (PatientClientCallBack i:callThePatientClient)
    {
      try {

        i.sharedPatients(event);
        System.out.println(i.toString());
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }



  private void sharedBookings(PropertyChangeEvent event)
  {
    for (BookingClientCallBack i:callTheBookingClient
    ) {
      try {
        i.sharedBookings(event);
        System.out.println(i.toString());
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }


  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("HCS",  this);
    System.out.println("ServerStart");
  }

  @Override
  public void registerClient(BookingClientCallBack bookingClientCallBack) {
    callTheBookingClient.add(bookingClientCallBack);


  }



  @Override public void registerPatientClient(
      PatientClientCallBack patientClientCallBack) throws RemoteException
  {
    callThePatientClient.add(patientClientCallBack);
  }

  @Override public void unregisterBookingClient(
      BookingClientCallBack bookingClientCallBack) throws RemoteException
  {
    callTheBookingClient.remove(bookingClientCallBack);
  }

  @Override public void unregisterPatientClient(
      PatientClientCallBack patientClientCallBack) throws RemoteException
  {
callThePatientClient.remove(patientClientCallBack);
  }


  @Override public String Login(String username, String password)
  {
    return loginModelServer.Login(username,password);
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)

  {
    userModelServer
        .CreateUser(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<User> GetUsers()
  {
    System.out.println("server");
    return userModelServer.GetUsers();
  }

  @Override public void RemoveUser(String username) throws RemoteException
  {
    userModelServer.RemoveUser(username);
  }

  @Override public void updateUser(String username, User user)
  {
    userModelServer.updateUser(username, user);
  }

  @Override public boolean patientExist(String cprNumber)
  {
    return patientModelServer.patientExist(cprNumber);
  }

  @Override public void createPatient(Patient patient)
  {
   // System.out.println("ReceptionServer");
  //  boolean find=patientModelServer.patientExist(patient.getCprNumber());
   // if ()
    patientModelServer.createPatient(patient);
  }

  @Override public void removePatient(String cprNumber) throws RemoteException
  {
    patientModelServer.removePatient(cprNumber);
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
      throws RemoteException
  {
    patientModelServer.updatePatient(cprNumber, patient);
   // bookingModelServer.GetBookings();
  }

  @Override public ArrayList<Patient> GetPatients() throws RemoteException
  {
    return patientModelServer.GetPatients();
  }

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
      throws RemoteException
  {
    return patientModelServer.GetSpecificPatients(search);
  }

  @Override public void createBooking(Booking booking) throws RemoteException
  {
    System.out.println("BookingServer");
    bookingModelServer.createBooking(booking);
  }

  @Override public ArrayList<Booking> GetBookings() throws RemoteException
  {
    return bookingModelServer.GetBookings();
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
      throws RemoteException
  {
    bookingModelServer.removeBooking(bookingDate, bookingTime);
  }

  @Override public void updateBooking(Date bookingDate, String bookingTime,
      Booking booking) throws RemoteException
  {
    bookingModelServer.updateBooking(bookingDate, bookingTime, booking);
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingModelServer.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetBookingsBYCprNumber(String cprNumber)
  {
    return bookingModelServer.GetBookingsBYCprNumber(cprNumber);
  }

  @Override public ArrayList<Booking> GetBookingsByDate(Date date)
      throws RemoteException
  {
    return bookingModelServer.GetBookingsByDate(date);
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
      throws RemoteException
  {
    return bookingModelServer.isPatientHasABooking(cprNumber);
  }

  @Override public boolean UserExist(String username) throws RemoteException
  {
    return userModelServer.UserExist(username);
  }

  @Override public void createPrescription(Prescription prescription)
      throws RemoteException
  {
    prescriptionModelServer.createPrescription(prescription);
  }

  @Override public ArrayList<Prescription> getPrescriptions()
      throws RemoteException
  {
    return prescriptionModelServer.getPrescriptions();
  }

  @Override public boolean isBookingHasAPrescription(Date bookingDate,
      String bookingTime) throws RemoteException
  {
    return bookingModelServer.isBookingHasAPrescription(bookingDate, bookingTime);
  }

  @Override public void removePrescription(Prescription prescription)
      throws RemoteException
  {
    prescriptionModelServer.removePrescription(prescription);
  }

  @Override public void updatePrescription(Date bookingDate, String bookingTime,
      String prescriptionType, String newPrescriptionType,
      String prescriptionText) throws RemoteException
  {
    prescriptionModelServer.updatePrescription(bookingDate, bookingTime, prescriptionType, newPrescriptionType, prescriptionText);
  }

  @Override public ArrayList<Prescription> getPrescriptionsByPatient(
      String cprNumber) throws RemoteException
  {
    return prescriptionModelServer.getPrescriptionsByPatient(cprNumber);
  }

  @Override public ArrayList<Prescription> getPrescriptionsByDate(Date date)
      throws RemoteException
  {
    return prescriptionModelServer.getPrescriptionsByDate(date);
  }

  @Override public ArrayList<String> getPrescriptionsType()
      throws RemoteException
  {
    return prescriptionModelServer.getPrescriptionsType();
  }

}
