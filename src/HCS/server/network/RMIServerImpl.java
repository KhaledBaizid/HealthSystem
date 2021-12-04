package HCS.server.network;

import HCS.shared.ClientReceptionCallBack;
import HCS.server.model.ServerModel;
import HCS.shared.ClientCallBack;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
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
  private final ServerModel model;
  private List<ClientCallBack> clients;
  private List<ClientReceptionCallBack> clients1;
  //private Object ClientloginCallBack;

  public RMIServerImpl(ServerModel model) throws RemoteException
  {
    UnicastRemoteObject.exportObject( this, 0);
    this.model = model;
    clients= new ArrayList<>();
    clients1=new ArrayList<>();
   // model.addListener(RequestType.RECEIVE_PUBLIC.toString(),this::publicMessageSent);
   // model.addListener(RequestType.GET_ACTIVE_USERS.toString(),this::userAdded);
  //  model.addListener(RequestType.UPDATE_ACTIVE_USERS.toString(),this::userdeleted);
 //   model.addListener("HCSLogin",this::HCS);
  //  model.addListener("HCSGetRoles",this::sharedRoles);
    model.addListener("HCSGetBookings",this::sharedBookings);
   // model.addListener("PtientHasBooking",this::patientHasBooking);
    model.addListener("HCSGetPatients",this::sharedPatients);


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

 /* private void publicMessageSent(PropertyChangeEvent event) {
    for (ClientCallBack i : clients){
      try {
        i.publicMessageSent(event);
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

/*  @Override public boolean loginUser(User user)
  {
    System.out.println("UserloginServer");
    return model.loginUser(user);
  }


  @Override public ArrayList<String> sendActiveUsersToClient()
  {
 return model.sendActiveUsersToClient();
  }

  @Override public void sendPublicMessage(Message message)
  {
  model.sendPublicMessage(message);
  }

  @Override public void disconnect(User userDisconnecting)
  {
  model.disconnect(userDisconnecting);

  }*/

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
    return model.Login(username,password);
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)

  {
     model.CreateUser(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<User> GetUsers()
  {
    System.out.println("server");
    return model.GetUsers();
  }

  @Override public void RemoveUser(String username) throws RemoteException
  {
    model.RemoveUser(username);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionServer");
  model.createPatient(patient);
  }

  @Override public void removePatient(String cprNumber) throws RemoteException
  {
    model.removePatient(cprNumber);
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
      throws RemoteException
  {
    model.updatePatient(cprNumber, patient);
    model.GetBookings();
  }

  @Override public ArrayList<Patient> GetPatients() throws RemoteException
  {
    return model.GetPatients();
  }

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
      throws RemoteException
  {
    return model.GetSpecificPatients(search);
  }

  @Override public void createBooking(Booking booking) throws RemoteException
  {
    System.out.println("BookingServer");
    model.createBooking(booking);
  }

  @Override public ArrayList<Booking> GetBookings() throws RemoteException
  {
    return model.GetBookings();
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
      throws RemoteException
  {
    model.removeBooking(bookingDate, bookingTime);
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return model.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetPatientBookings(String cprNumber)
  {
    return model.GetPatientBookings(cprNumber);
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
      throws RemoteException
  {
    return model.isPatientHasABooking(cprNumber);
  }

  @Override public boolean UserExist(String username) throws RemoteException
  {
    return model.UserExist(username);
  }

}
