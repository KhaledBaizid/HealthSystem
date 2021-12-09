package HCS.server.model;

import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

import java.util.ArrayList;

public interface PatientModelServer extends Subject
{
  boolean patientExist(String cprNumber);
  void createPatient(Patient patient);
  void removePatient(String cprNumber);
  void updatePatient(String cprNumber,Patient patient);
  ArrayList<Patient> GetPatients();
  ArrayList<Patient> GetSpecificPatients(String search);
}
