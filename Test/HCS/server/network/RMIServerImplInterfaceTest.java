package HCS.server.network;

import HCS.shared.ClientReceptionCallBack;
import HCS.DataBase.*;
import HCS.server.model.ServerModelImpl;
import HCS.shared.ClientCallBack;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.User;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

class RMIServerImplInterfaceTest
{ ManageAdminDAO userDAO = new ManageAdminDAO()
{
/*  @Override public void createUser(String username)
  {

  }*/

 /* @Override public boolean roleExist(String username)
  {
    return false;
  }*/

  @Override public boolean UserExist(String username)
  {
    return false;
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {

  }

  @Override public ArrayList<User> GetUsers()
  {
    return null;
  }

  @Override public void RemoveUser(String username)
  {

  }

  /*@Override public void HCSRemoveRole(String username)
  {

  }*/

  @Override public void HCRUpdateRole(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {

  }

  /*@Override public void createPatient(String cprnumber, String firstname,
      String lastname)
  {

  }*/
};
  ManagePatientDAO receptionDAO;
  ManageLoginDAO loginDAO;
  ManageBookingDAO bookingDAO;
  ManagePrescriptionDAO prescriptionDAO;
  RMIServerImpl server1;
  RMIServer server=new RMIServer()
  {
    @Override public void registerClient(ClientCallBack clientCallBack)
        throws RemoteException
    {

    }

    @Override public void registerClient(ClientCallBack clientCallBack,
        ClientReceptionCallBack clientReceptionCallBack) throws RemoteException
    {

    }

    @Override public void unregisterClient(ClientCallBack clientCallBack)
        throws RemoteException
    {

    }

    @Override public String Login(String username, String password)
        throws RemoteException
    {
      return null;
    }

    @Override public void CreateUser(String firstname, String lastname,
        Date birthday, String username, String password, String role)
        throws RemoteException
    {

    }

    @Override public ArrayList<User> GetUsers() throws RemoteException
    {
      return null;
    }

    @Override public void RemoveUser(String username) throws RemoteException
    {

    }

    @Override public void createPatient(Patient patient) throws RemoteException
    {

    }

    @Override public void removePatient(String cprNumber) throws RemoteException
    {

    }

    @Override public void updatePatient(String cprNumber, Patient patient)
        throws RemoteException
    {

    }

    @Override public ArrayList<Patient> GetPatients() throws RemoteException
    {
      return null;
    }

    @Override public ArrayList<Patient> GetSpecificPatients(String search)
        throws RemoteException
    {
      return null;
    }

    @Override public void createBooking(Booking booking) throws RemoteException
    {

    }

    @Override public ArrayList<Booking> GetBookings() throws RemoteException
    {
      return null;
    }

    @Override public void removeBooking(Date bookingDate, String bookingTime)
        throws RemoteException
    {

    }

    @Override public ArrayList<String> getAvailableTime(Date date)
        throws RemoteException
    {
      return null;
    }

    @Override public ArrayList<Booking> GetPatientBookings(String cprNumber)
    {
      return null;
    }

    @Override public boolean isPatientHasABooking(String cprNumber)
        throws RemoteException
    {
      return false;
    }

    @Override public boolean UserExist(String username) throws RemoteException
    {
      return false;
    }
  };
  ServerModelImpl serverModelImpl = new ServerModelImpl( userDAO,receptionDAO, loginDAO,bookingDAO,prescriptionDAO);

  {
    try
    {
      System.out.println("");
      server1 = new RMIServerImpl(serverModelImpl);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Test void roleExist()
  {
    boolean exist=false;
    try
    {
      exist=server1.UserExist("a");
      System.out.println(exist);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}