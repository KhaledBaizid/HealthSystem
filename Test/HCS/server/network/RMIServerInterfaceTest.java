package HCS.server.network;

import HCS.DataBase.ManageAdminDAO;
import HCS.DataBase.ManageLoginDAO;
import HCS.DataBase.ManageReceptionDAO;
import HCS.server.model.ServerModel;
import HCS.shared.ClientCallBack;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RMIServerInterfaceTest
{ ManageAdminDAO userDAO = new ManageAdminDAO()
{
  @Override public void createUser(String username)
  {

  }

  @Override public boolean roleExist(String username)
  {
    return false;
  }

  @Override public void HCSCreateRole(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {

  }

  @Override public ArrayList<Role> HCSGetRoles()
  {
    return null;
  }

  @Override public void HCSRemoveRole(String username)
  {

  }

  @Override public void HCRUpdateRole(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {

  }

  @Override public void createPatient(String cprnumber, String firstname,
      String lastname)
  {

  }
};
  ManageReceptionDAO receptionDAO;
  ManageLoginDAO loginDAO;
  RMIServer server1;
  RMIServerInterface server=new RMIServerInterface()
  {
    @Override public void registerClient(ClientCallBack clientCallBack)
        throws RemoteException
    {

    }

    @Override public void unregisterClient(ClientCallBack clientCallBack)
        throws RemoteException
    {

    }

    @Override public String HCSLogin(String username, String password)
        throws RemoteException
    {
      return null;
    }

    @Override public void HCSCreateRole(String firstname, String lastname,
        Date birthday, String username, String password, String role)
        throws RemoteException
    {

    }

    @Override public ArrayList<Role> HCSGetRoles() throws RemoteException
    {
      return null;
    }

    @Override public void HCSRemoveRole(String username) throws RemoteException
    {

    }

    @Override public void createPatient(Patient patient) throws RemoteException
    {

    }

    @Override public ArrayList<Patient> HCSGetPatients() throws RemoteException
    {
      return null;
    }

    @Override public ArrayList<Patient> HCSGetSpecificPatients(String search)
        throws RemoteException
    {
      return null;
    }

    @Override public void createBooking(Booking booking) throws RemoteException
    {

    }

    @Override public ArrayList<Booking> HCSGetBookings() throws RemoteException
    {
      return null;
    }

    @Override public void removeBooking(Date bookingDate, String bookingTime)
        throws RemoteException
    {

    }

    @Override public ArrayList<String> getTimeAvailable(Date date)
        throws RemoteException
    {
      return null;
    }

    @Override public boolean roleExist(String username) throws RemoteException
    {
      return false;
    }
  };
  ServerModel model= new ServerModel( userDAO,receptionDAO, loginDAO);

  {
    try
    {
      server1 = new RMIServer(model);
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
      exist=server1.roleExist("a");
      System.out.println(exist);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}