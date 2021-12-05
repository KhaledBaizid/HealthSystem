package HCS.DataBase;

import HCS.shared.transferObjects.Prescription;
import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;

public interface ManagePrescriptionDAO
{
  ArrayList<String> getPrescreptionsType();
  //void createPrescription(Date bookingDate,String bookingTime,String prescriptionType, String prescriptionText);
  void createPrescription(Prescription prescription);
  String getSpecificPrescription(Date bookingDate,String bookingTime,String prescriptionType);
  ArrayList<Prescription>  getPrescriptions();
}
