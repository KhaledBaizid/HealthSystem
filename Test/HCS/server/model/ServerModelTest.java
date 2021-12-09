package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
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
    String str="2015-03-31";
    Date date=Date.valueOf(str);
    patientDAO.removePatient("11001122");
    Patient patient=new Patient("11001122","bob","sep2",date,"M","anywhere","12101210","anymail");
    patientDAO.createPatient(patient);
    assertTrue(patientDAO.patientExist("11001122"));
    patientDAO.removePatient("11001122");
  }

  @Test void removePatient()
  {
    String str="2015-03-31";
    Date date=Date.valueOf(str);
    patientDAO.removePatient("11001122");
    Patient patient=new Patient("11001122","bob","sep2",date,"M","anywhere","12101210","anymail");
    patientDAO.createPatient(patient);
    assertTrue(patientDAO.patientExist("11001122"));
    patientDAO.removePatient("11001122");
    assertFalse(patientDAO.patientExist("11001122"));
  }

  @Test void updatePatient()
  {
    String str="2015-03-31";
    Date date=Date.valueOf(str);
    String str1="2015-02-14";
    Date newDate=Date.valueOf(str1);
    patientDAO.removePatient("11001122");
    Patient patient=new Patient("11001122","bob","sep2",date,"M","anywhere","12101210","anymail");
    Patient newPatientInfo=new Patient("11001122","sara","baizid",newDate,"F","tilst","00000000","sara@baizid.com");
    patientDAO.createPatient(patient);
    patientDAO.updatePatient("11001122",newPatientInfo);
    Patient updatedPatient= patientDAO.GetSpecificPatients("11001122").get(0);

    assertEquals(updatedPatient.getCprNumber(), "11001122");
    assertEquals(updatedPatient.getFirstname(), "sara");
    assertEquals(updatedPatient.getLastname(), "baizid");
    assertEquals(newDate, updatedPatient.getBirthday());
    assertEquals(updatedPatient.getSex(), "F");
    assertEquals(updatedPatient.getAddress(), "tilst");
    assertEquals(updatedPatient.getPhone(), "00000000");
    assertEquals(updatedPatient.getMail(), "sara@baizid.com");

    patientDAO.removePatient("11001122");


  }

  @Test void getPatients()
  {
  }

  @Test void getSpecificPatients()
  {
    String str1="2015-02-14";
    Date newDate=Date.valueOf(str1);
    patientDAO.removePatient("11001122");
    Patient patient=new Patient("11001122","bob","sep2",date,"M","anywhere","12101210","anymail");
    patientDAO.createPatient(patient);
    ArrayList<Patient> patients= patientDAO.GetSpecificPatients("11001122");
    assertTrue(patients.get(0).getCprNumber().equals("11001122") && patients.size()==1);

    Patient patient1=new Patient("11001123","elias","sep2",date,"M","tilst","14578253","mail");
    patientDAO.createPatient(patient1);
    patients.clear();
    patients= patientDAO.GetSpecificPatients("1100112");
    assertTrue( patients.size()==2);

    patientDAO.removePatient("11001122");
    patientDAO.removePatient("11001123");


  }

  ///
  @Test void createBooking()
  {
    String str="1990-03-31";
    Date date=Date.valueOf(str);
    patientDAO.removePatient("11001122");
    Patient patient=new Patient("11001122","bob","sep2",date,"M","anywhere","12101210","anymail");
    patientDAO.createPatient(patient);
    Booking booking=new Booking(date,"08:00","tired","11001122");
    bookingDAO.createBooking(booking);
    assertTrue(bookingDAO.bookingExist(date,"08:00"));
    bookingDAO.removeBooking(date,"08:00");
    patientDAO.removePatient("11001122");

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