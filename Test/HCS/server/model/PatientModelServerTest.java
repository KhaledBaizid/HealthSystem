package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatientModelServerTest
{
  LoginDAO loginDAO;
  UserDAO adminDAO;
  PatientDAO patientDAO;

  Date date= new Date(-1970);

  @BeforeEach
  public void arrange() {

    loginDAO = LoginDAO.getInstance();
    adminDAO= UserDAO.getInstance();
    patientDAO=PatientDAO.getInstance();


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

  @Test void getPatients()
  {
   /* String str="2021-12-09";
    Date date=Date.valueOf(str);
    boolean find=prescriptionDAO.isBookingHasAPrescription(date,"08:15");
    assertTrue(find);*/
  }

}