package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class ServerModel implements ServerModelInterface {

    private PropertyChangeSupport support;
   // private ArrayList<User> connectedUsers;
    ArrayList<String> activeUsernames = new ArrayList<>();
    private ManageAdminDAO userDAO;
    private ManageReceptionDAO receptionDAO;
    private ManageLoginDAO loginDAO;
   // private MainDAO mainDAO;
  //UserDAOImpl

    public ServerModel(ManageAdminDAO userDAO,ManageReceptionDAO receptionDAO,ManageLoginDAO loginDAO)

    {   this.receptionDAO=receptionDAO;
        this.userDAO=userDAO;
        this.loginDAO=loginDAO;
        support = new PropertyChangeSupport(this);
       // connectedUsers = new ArrayList<>();
    }
 /* public ServerModel(MainDAO mainDAO)

  {   this.mainDAO=mainDAO;
     // this.userDAO=userDAO;
      support = new PropertyChangeSupport(this);
      connectedUsers = new ArrayList<>();
  }*/

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override public void removeListener(String eventName,
        PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(eventName, listener);

    }


  /*  @Override
    public boolean loginUser(User user) {
        System.out.println("modelServerLogin");

        Boolean existingUser = false;

        for (User i :connectedUsers) {
            if (i.getUsername().equals(user.getUsername()))
            {
                existingUser = true;
                break;
            }
        }

        if (!existingUser){
            connectedUsers.add(user);
            System.out.println(user.getUsername() + " logged in successfully");

            activeUsernames.add(user.getUsername());
            updateActiveUsers(user.getUsername());
            ///////////////
            userDAO.createUser(user.getUsername());
            ///////////
            return true;
        }else{

            Request loginResultRequest = new Request(RequestType.EXISTING_USERNAME, user);
            support.firePropertyChange(RequestType.EXISTING_USERNAME.toString(), null, loginResultRequest);
            return false;
        }
    }

    @Override
    public ArrayList<String> sendActiveUsersToClient() {
        return activeUsernames;
    }

    @Override
    public void sendPublicMessage(Message messageToReceive) {
        Request sendPublicMessageRequest = new Request(RequestType.RECEIVE_PUBLIC, messageToReceive);
        support.firePropertyChange(sendPublicMessageRequest.getType().toString(), null, sendPublicMessageRequest);
    }

    @Override
    public void disconnect(User user) {

        connectedUsers
            .removeIf(i -> i.getUsername().equals(user.getUsername()));
        activeUsernames.remove(user.getUsername());
        support.firePropertyChange(RequestType.UPDATE_ACTIVE_USERS.toString(),null,user.getUsername());

    }*/

    @Override public String HCSLogin(String username, String password)
    {           //  support.firePropertyChange("HCSLogin", null, userDAO.HCSLogin(username, password));

        String role= loginDAO.HCSLogin(username, password);
      System.out.println(role);
     // String role= mainDAO.getManageUserDAO().HCSLogin(username, password);
       return role;
    }

  @Override public boolean roleExist(String username)
  {
    boolean b;
        b=userDAO.roleExist(username);
       // b=true;
    System.out.println(b);
    System.out.println(b);
    System.out.println(b);
    return b;
  }

  @Override public void HCSCreateRole(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {
        userDAO.HCSCreateRole(firstname, lastname, birthday, username, password, role);
    }

    @Override public ArrayList<Role> HCSGetRoles()
    {
        System.out.println("servermodel");
        ArrayList<Role> role1;
        role1=userDAO.HCSGetRoles();
        //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
       // return userDAO.HCSGetRoles();
        support.firePropertyChange("HCSGetRoles",null,role1);
        return role1;
    }

    @Override public void HCSRemoveRole(String username)
    {
        userDAO.HCSRemoveRole(username);
    }

    @Override public void createPatient(Patient patient)
    {
        System.out.println("ReceptionModelServer");
     receptionDAO.createPatient(patient);
      //  userDAO.createPatient(cprnumber, firstname, Lastname);

    }

  @Override public ArrayList<Patient> HCSGetPatients()
  {
    ArrayList<Patient> patients;
   patients=receptionDAO.HCSGetPatients();
    return patients;
  }

  @Override public ArrayList<Patient> HCSGetSpecificPatients(String search)
  {
    ArrayList<Patient> patients;
    patients=receptionDAO.HCSGetSpecificPatients(search);
    return patients;
  }

  @Override public void createBooking(Booking booking)
  {
    System.out.println("BookingServerModel");
    receptionDAO.createBooking(booking);
  }

  @Override public ArrayList<Booking> HCSGetBookings()
  {
    ArrayList<Booking> booking1;
    booking1=receptionDAO.HCSGetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    receptionDAO.removeBooking(bookingDate, bookingTime);
  }

  @Override public ArrayList<String> getTimeAvailable(Date date)
  {
    return receptionDAO.getTimeAvailable(date);
  }

   /* private void updateActiveUsers(String username) {

       support.firePropertyChange(RequestType.GET_ACTIVE_USERS.toString(),null,username);
    }*/
}
