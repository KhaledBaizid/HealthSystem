package HCS.DataBase;

import HCS.shared.transferObjects.Prescription;
import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;

public interface ManagePrescriptionDAO
{
  ArrayList<String> getPrescreptionsType();

  void createPrescription(Prescription prescription);
  String getSpecificPrescription(Date bookingDate,String bookingTime,String prescriptionType);
  ArrayList<Prescription>  getPrescriptions();
  boolean isBookingHasAPrescription(Date bookingDate,String bookingTime);
  void removePrescription(Prescription prescription);
  void updatePrescription(Date bookingDate,String bookingTime, String prescriptionType, String newPrescriptionType,String prescriptionText);

  ArrayList<Prescription> getPrescriptionsByPatient(String cprNumber);
  ArrayList<Prescription> getPrescriptionsByDate(Date date);

}
