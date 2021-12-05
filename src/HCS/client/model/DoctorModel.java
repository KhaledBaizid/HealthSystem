package HCS.client.model;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.utility.Subject;

public interface DoctorModel extends Subject
{
  void HCSGetBookings();

  void createPrescription(Prescription prescription);
  void  getPrescriptions();
}
