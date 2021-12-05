package HCS.client.network;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.utility.Subject;

import java.util.ArrayList;

public interface DoctorClient extends Subject
{
  void startClient();
  void HCSGetBookings();

  void createPrescription(Prescription prescription);
  void  getPrescriptions();
}
