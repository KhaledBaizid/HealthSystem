package HCS.server.network;

import HCS.server.model.ServerModelInterface;
import HCS.shared.ClientCallBack;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
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

public class RMIServer implements RMIServerInterface
{
  private final ServerModelInterface model;
  private List<ClientCallBack> clients;

  public RMIServer(ServerModelInterface model) throws RemoteException
  {
    UnicastRemoteObject.exportObject( this, 0);
    this.model = model;
    clients= new ArrayList<>();
   // model.addListener(RequestType.RECEIVE_PUBLIC.toString(),this::publicMessageSent);
   // model.addListener(RequestType.GET_ACTIVE_USERS.toString(),this::userAdded);
  //  model.addListener(RequestType.UPDATE_ACTIVE_USERS.toString(),this::userdeleted);
 //   model.addListener("HCSLogin",this::HCS);
    model.addListener("HCSGetRoles",this::sharedRoles);
    model.addListener("HCSGetBookings",this::sharedBookings);

  }

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

  private void sharedRoles(PropertyChangeEvent event)
  {
    for (ClientCallBack i:clients
    ) {
      try {
        i.sharedroles(event);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }
  }

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
    registry.bind("Chat",  this);
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

  @Override public void unregisterClient(ClientCallBack clientCallBack)

  {
    clients.remove(clientCallBack);

  }

  @Override public String HCSLogin(String username, String password)
  {
    return model.HCSLogin(username,password);
  }

  @Override public void HCSCreateRole(String firstname, String lastname,
      Date birthday, String username, String password, String role)

  {
     model.HCSCreateRole(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<Role> HCSGetRoles()
  {
    System.out.println("server");
    return model.HCSGetRoles();
  }

  @Override public void HCSRemoveRole(String username) throws RemoteException
  {
    model.HCSRemoveRole(username);
  }

  @Override public void createPatient(Patient patient)
  {
    System.out.println("ReceptionServer");
  model.createPatient(patient);
  }

  @Override public ArrayList<Patient> HCSGetPatients() throws RemoteException
  {
    return model.HCSGetPatients();
  }

  @Override public ArrayList<Patient> HCSGetSpecificPatients(String search)
      throws RemoteException
  {
    return model.HCSGetSpecificPatients(search);
  }

  @Override public void createBooking(Booking booking) throws RemoteException
  {
    System.out.println("BookingServer");
    model.createBooking(booking);
  }

  @Override public ArrayList<Booking> HCSGetBookings() throws RemoteException
  {
    return model.HCSGetBookings();
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
      throws RemoteException
  {
    model.removeBooking(bookingDate, bookingTime);
  }

  @Override public ArrayList<String> getTimeAvailable(Date date)
  {
    return model.getTimeAvailable(date);
  }

  @Override public boolean roleExist(String username) throws RemoteException
  {
    return model.roleExist(username);
  }

}
