package HCS.client.network;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface PrescriptionClient extends Subject
{
  void startClient();


  void createPrescription(Prescription prescription);
  void  getPrescriptions();
  void removePrescription(Prescription prescription);
  void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText);
  void getPrescriptionsByPatient(String cprNumber);
  void getPrescriptionsByDate(Date date);
  ArrayList<String> getPrescriptionsType();
}
