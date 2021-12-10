package HCS.client.model;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.utility.Subject;

import java.sql.Date;

public interface PrescriptionModel extends Subject
{
 // void HCSGetBookings();

  void createPrescription(Prescription prescription);
  void  getPrescriptions();
  void removePrescription(Prescription prescription);
  void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText);
}
