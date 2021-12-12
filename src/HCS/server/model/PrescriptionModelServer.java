package HCS.server.model;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface PrescriptionModelServer extends Subject
{
  void createPrescription(Prescription prescription);
  ArrayList<Prescription>  getPrescriptions();
  void removePrescription(Prescription prescription);
  void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText);
  ArrayList<Prescription> getPrescriptionsByPatient(String cprNumber);
  ArrayList<Prescription> getPrescriptionsByDate(Date date);

  ArrayList<String> getPrescriptionsType();
}
