package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class ServerModelImpl implements ServerModel
{

    private PropertyChangeSupport support;
   // private ArrayList<User> connectedUsers;
    ArrayList<String> activeUsernames = new ArrayList<>();
    private ManageAdminDAO adminDAO;
    private ManagePatientDAO patientDAO;
    private ManageLoginDAO loginDAO;
    private ManageBookingDAO bookingDAO;
    private ManagePrescriptionDAO prescriptionDAO;
   // private MainDAO mainDAO;
  //UserDAOImpl

    public ServerModelImpl(ManageAdminDAO userDAO, ManagePatientDAO receptionDAO,ManageLoginDAO loginDAO,ManageBookingDAO bookingDAO,ManagePrescriptionDAO prescriptionDAO)

    {   this.patientDAO=receptionDAO;
        this.adminDAO=userDAO;
        this.loginDAO=loginDAO;
        this.bookingDAO=bookingDAO;
        this.prescriptionDAO=prescriptionDAO;
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



    @Override public String Login(String username, String password)
    {           //  support.firePropertyChange("HCSLogin", null, userDAO.HCSLogin(username, password));

        String role= loginDAO.Login(username, password);
      System.out.println(role);
     // String role= mainDAO.getManageUserDAO().HCSLogin(username, password);
       return role;
    }

  @Override public boolean UserExist(String username)
  {
    boolean b;
        b=adminDAO.UserExist(username);
    return b;
  }

  @Override public void CreateUser(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {
        adminDAO.CreateUser(firstname, lastname, birthday, username, password, role);
    }

    @Override public ArrayList<User> GetUsers()
    {
        System.out.println("servermodel");
        ArrayList<User> user1;
        user1 =adminDAO.GetUsers();
        //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
       // return userDAO.HCSGetRoles();
        support.firePropertyChange("HCSGetRoles",null, user1);
        return user1;
    }

    @Override public void RemoveUser(String username)
    {
        adminDAO.RemoveUser(username);
    }

    @Override public void createPatient(Patient patient)
    {
        System.out.println("ReceptionModelServer");
     patientDAO.createPatient(patient);
      //  userDAO.createPatient(cprnumber, firstname, Lastname);

    }

  @Override public void removePatient(String cprNumber)
  {
    if( !bookingDAO.isPatientHasABooking(cprNumber))
    {
      patientDAO.removePatient(cprNumber);
    }
  /*  else
    {
      String s="h";
      support.firePropertyChange("PtientHasBooking",null,s);
    }*/
  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    patientDAO.updatePatient(cprNumber, patient);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);

  }

  @Override public ArrayList<Patient> GetPatients()
  {
    ArrayList<Patient> patients;
   patients=patientDAO.GetPatients();
    support.firePropertyChange("HCSGetPatients",null, patients);
    return patients;
  }

  @Override public ArrayList<Patient> GetSpecificPatients(String search)
  {
    ArrayList<Patient> patients;
    patients=patientDAO.GetSpecificPatients(search);
    return patients;
  }

  @Override public void createBooking(Booking booking)
  {
    System.out.println("BookingServerModel");
    bookingDAO.createBooking(booking);
    /////////
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
  }

  @Override public ArrayList<Booking> GetBookings()
  {
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
   // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public void removeBooking(Date bookingDate, String bookingTime)
  {
    bookingDAO.removeBooking(bookingDate, bookingTime);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetBookings();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetBookings",null,booking1);
  }

  @Override public ArrayList<String> getAvailableTime(Date date)
  {
    return bookingDAO.getAvailableTime(date);
  }

  @Override public ArrayList<Booking> GetPatientBookings(String cprNumber)
  {
   // return bookingDAO.GetPatientBookings(cprNumber);
    ArrayList<Booking> booking1;
    booking1=bookingDAO.GetPatientBookings(cprNumber);
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
   // support.firePropertyChange("HCSGetBookings",null,booking1);
    return booking1;
  }

  @Override public boolean isPatientHasABooking(String cprNumber)
  {
    return bookingDAO.isPatientHasABooking(cprNumber);
  }

   /* private void updateActiveUsers(String username) {

       support.firePropertyChange(RequestType.GET_ACTIVE_USERS.toString(),null,username);
    }*/
}
