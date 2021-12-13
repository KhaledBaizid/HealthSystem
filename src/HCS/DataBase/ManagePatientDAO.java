package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.User;

import java.util.ArrayList;

public interface ManagePatientDAO
{
  void createPatient(Patient patient);

  void removePatient(String cprNumber);

  void updatePatient(String cprNumber,Patient patient);
  ArrayList<Patient> GetPatients();
  ArrayList<Patient> GetSpecificPatients(String search);

  boolean patientExist(String cprNumber);

}
