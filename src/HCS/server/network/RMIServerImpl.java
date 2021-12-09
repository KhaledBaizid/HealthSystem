package HCS.server.network;

import HCS.server.model.*;
import HCS.shared.ClientReceptionCallBack;
import HCS.shared.ClientCallBack;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.User;
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
 // private final ServerModel model;
  private final LoginModelServer loginModelServer;
  private final AdminModelServer adminModelServer;
  private final PatientModelServer patientModelServer;
  private final BookingModelServer bookingModelServer;
  private final PrescriptionModelServer prescriptionModelServer;
  private List<ClientCallBack> clients;
  private List<ClientReceptionCallBack> clients1;
  //private Object ClientloginCallBack;
 // public RMIServerImpl(ServerModel model) throws RemoteException
  public RMIServerImpl(/*ServerModel model,*/ LoginModelServer loginModelServer,AdminModelServer adminModelServer, PatientModelServer patientModelServer,BookingModelServer bookingModelServer, PrescriptionModelServer prescriptionModelServer) throws RemoteException
  {
    UnicastRemoteObject.exportObject( this, 0);
    //this.model = model;
    clients= new ArrayList<>();
    clients1=new ArrayList<>();
    this.loginModelServer = loginModelServer;
    this.adminModelServer=adminModelServer;
    this.patientModelServer=patientModelServer;
    this.bookingModelServer=bookingModelServer;
    this.prescriptionModelServer=prescriptionModelServer;

  //  model.addListener("HCSGetRoles",this::sharedRoles);
    bookingModelServer.addListener("HCSGetBookings",this::sharedBookings);
    patientModelServer.addListener("HCSGetBookings",this::sharedBookings);
   // model.addListener("PtientHasBooking",this::patientHasBooking);
    patientModelServer.addListener("HCSGetPatients",this::sharedPatients);



  }

  private void sharedPatients(PropertyChangeEvent event)
  {
    for (ClientReceptionCallBack i:clients1)
    {
      try {
        i.sharedPatients(event);
        System.out.println(i.toString());
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }

 /* private void patientHasBooking(PropertyChangeEvent event)
  {    for (ClientReceptionCallBack i:clients1)
   {
    try {
      i.patientHasBooking(event);
      System.out.println(i.toString());
    } catch (RemoteException e) {
      e.printStackTrace();
    }

  }
  }*/

  private void sharedBookings(PropertyChangeEvent event)
  {
    for (ClientCallBack i:clients
    ) {
      try {
        i.sharedBookings(event);
        System.out.println(i.toString());
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }

/*  private void sharedRoles(PropertyChangeEvent event)
  {
    for (ClientCallBack i:clients
    ) {
      try {
        i.sharedroles(event);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }*/

  private void HCS(PropertyChangeEvent event)
  {
  }

 /* private void userAdded(PropertyChangeEvent event) {
    for (ClientCallBack i:clients
         ) {
      try {
        i.userAdded(event);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }*/

 /* private void userdeleted(PropertyChangeEvent event) {

    for (ClientCallBack i:clients
    ) {
      try {
        i.userDeleted(event);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }*/




  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("HCS",  this);
    System.out.println("ServerStart");
  }

  @Override
  public void registerClient(ClientCallBack clientCallBack) {
    clients.add(clientCallBack);


  }

  @Override public void registerClient(ClientCallBack clientCallBack,
      ClientReceptionCallBack clientReceptionCallBack) throws RemoteException
  {
    clients.add(clientCallBack);
    clients1.add(clientReceptionCallBack);
  }

  @Override public void unregisterClient(ClientCallBack clientCallBack)

  {
    clients.remove(clientCallBack);

  }

  @Override public String Login(String username, String password)
  {
    return loginModelServer.Login(username,password);
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)

  {
    adminModelServer.CreateUser(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<User> GetUsers()
  {
    System.out.println("server");
    return adminModelServer.GetUsers();
  }

  @Override public void RemoveUser(String username) throws RemoteException
  {
    adminModelServer.RemoveUser(username);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionServer");
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
    bookingModelServer.GetBookings();
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

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingModelServer.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetPatientBookings(String cprNumber)
  {
    return bookingModelServer.GetPatientBookings(cprNumber);
  }

  @Override public ArrayList<Booking> GetPatientBookingsByDate(Date date)
      throws RemoteException
  {
    return bookingModelServer.GetPatientBookingsByDate(date);
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
      throws RemoteException
  {
    return bookingModelServer.isPatientHasABooking(cprNumber);
  }

  @Override public boolean UserExist(String username) throws RemoteException
  {
    return adminModelServer.UserExist(username);
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

}
