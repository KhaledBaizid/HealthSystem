package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class ServerModelTest
{
  LoginDAO loginDAO;
  AdminDAO adminDAO;
  PatientDAO patientDAO;
  BookingDAO bookingDAO;
  PrescriptionDAO prescriptionDAO;
  Date date= new Date(-1970);

  private final static SimpleDateFormat DATE_FORMAT = createFormat();

  private static SimpleDateFormat createFormat() {
    // Make sure there are no locale-specific nasties, either...
    SimpleDateFormat ret = new SimpleDateFormat("dd.mm.yyy", Locale.US);
   // ret.setTimeZone(TimeZone.getTimeZone("Etc/UTC");
    return ret;
  }
  @BeforeEach
  public void arrange() {

    loginDAO= LoginDAO.getInstance();
    adminDAO=AdminDAO.getInstance();
    patientDAO=PatientDAO.getInstance();
    bookingDAO=BookingDAO.getInstance();
    prescriptionDAO=PrescriptionDAO.getInstance();

  }


  @Test void login()
  {
    adminDAO.RemoveUser("administrator");
    adminDAO.RemoveUser("receptionist");
    adminDAO.RemoveUser("Doc");

    adminDAO.CreateUser("administrator","adminustrator",date,"administrator","adminstrator","ADMIN");
    adminDAO.CreateUser("receptionist","receptionist",date,"receptionist","receptionist","RECEPTION");
    adminDAO.CreateUser("Doc","Doc",date,"Doc","Doc","DOCTOR");

    String role=loginDAO.Login("administrator","adminstrator");
    assertTrue(role.equals("ADMIN"));

   role=loginDAO.Login("receptionist","receptionist");
    assertTrue(role.equals("RECEPTION"));

    role=loginDAO.Login("Doc","Doc");
    assertTrue(role.equals("DOCTOR"));

    role=loginDAO.Login("bbbb","abbb");
    assertTrue(role==null);
    adminDAO.RemoveUser("administrator");
    adminDAO.RemoveUser("receptionist");
    adminDAO.RemoveUser("Doc");



  }

  @Test void userExist()
  {
    adminDAO.RemoveUser("administrator");
    adminDAO.CreateUser("administrator","administrator",date,"administrator","adminstrator","ADMIN");
   boolean exist= adminDAO.UserExist("administrator");
    assertTrue(exist);

    exist= adminDAO.UserExist("adgaagagagshshshshwyhxc");
    assertFalse(exist);

  }

  @Test void createUser()
  {
    adminDAO.RemoveUser("administrator");
    adminDAO.CreateUser("administrator","administrator",date,"administrator","adminstrator","ADMIN");
    boolean exist= adminDAO.UserExist("administrator");
    assertTrue(exist);
    adminDAO.RemoveUser("administrator");
  }

  @Test void getUsers()
  {
    ArrayList<User> realUsers=adminDAO.GetUsers();
    String str="2015-03-31";
    Date date=Date.valueOf(str);

   String d= realUsers.get(0).getBirthday().toString();
   Date d1=Date.valueOf(d);

   adminDAO.CreateUser("a","a",d1,"xxxxxx","aaa","ADMIN");
    adminDAO.CreateUser("r","r",date,"yyyyyy","rrr","RECEPTION");
    adminDAO.CreateUser("d","d",date,"zzzzzz","ddd","DOCTOR");

    ArrayList<User> testUsers=adminDAO.GetUsers();
    testUsers.removeAll(realUsers);
    assertTrue(testUsers.get(0).getUsername().equals("xxxxxx") && testUsers.get(1).getUsername().equals("yyyyyy") && testUsers.get(2).getUsername().equals("zzzzzz") );
    assertFalse(testUsers.size()==2);
   adminDAO.RemoveUser("xxxxxx");
    adminDAO.RemoveUser("yyyyyy");
    adminDAO.RemoveUser("zzzzzz");

  }

  @Test void removeUser()
  {
    adminDAO.CreateUser("a","a",date,"xxxxxx","aaa","ADMIN");
    assertTrue(adminDAO.UserExist("xxxxxx"));
    adminDAO.RemoveUser("xxxxxx");
    assertTrue(!adminDAO.UserExist("xxxxxx"));

  }



  @Test void createPatient()
  {
  }

  @Test void removePatient()
  {
  }

  @Test void updatePatient()
  {
  }

  @Test void getPatients()
  {
  }

  @Test void getSpecificPatients()
  {
  }

  ///
  @Test void createBooking()
  {
  }

  @Test void getBookings()
  {
  }

  @Test void removeBooking()
  {
  }

  @Test void getAvailableTime()
  {
  }

  @Test void getPatientBookings()
  {
  }

  @Test void getPatientBookingsByDate()
  {
  }

  @Test void isPatientHasABooking()
  {
  }

  //
  @Test void createPrescription()
  {
  }

  @Test void getPrescriptions()
  {
  }
}