package HCS.client.network;

import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface PatientClient extends Subject
{
  void startClient();
  void Disconnect();

  void createPatient(Patient patient);
  void removePatient(String cprNumber);
  void updatePatient(String cprNumber,Patient patient);

  void GetPatients();
  void GetSpecificPatients(String search);

  boolean patientExist(String cprNumber);


}
