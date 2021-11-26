package HCS.server.network;

import HCS.shared.ClientCallBack;
import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.Role;
import HCS.shared.transferObjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface RMIServerInterface extends Remote
{

  boolean loginUser( User user) throws RemoteException;

  ArrayList<String> sendActiveUsersToClient() throws RemoteException;

  void sendPublicMessage(Message message)throws RemoteException;


  void disconnect(User userDisconnecting) throws RemoteException;
  void registerClient(ClientCallBack clientCallBack) throws RemoteException;

   void unregisterClient(ClientCallBack clientCallBack) throws RemoteException;

  //void addListener(String eventName, PropertyChangeListener listener) throws RemoteException;

  /////
  String HCSLogin(String username,String password) throws RemoteException;;
  void HCSCreateRole(String firstname,String lastname, Date birthday,String username,String password,String role)throws RemoteException;
  ArrayList<Role> HCSGetRoles() throws RemoteException;
  void HCSRemoveRole(String username) throws RemoteException;

  //void createPatient(String firstname,String Lastname);
  void createPatient(String cprnumber ,String firstname,String Lastname) throws RemoteException;;



  /////
}

