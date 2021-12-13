package HCS.client.model;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;
import HCS.shared.utility.Subject;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface PrescriptionModel extends Subject
{


  void createPrescription(Prescription prescription);
  void  getPrescriptions();
  void removePrescription(Prescription prescription);
  void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText);
  void getPrescriptionsByPatient(String cprNumber);
  void getPrescriptionsByDate(Date date);

  ArrayList<String> getPrescriptionsType();
}
