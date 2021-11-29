package HCS.client.model;

import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

public interface HCSModelReceptionInterface extends Subject
{
  void createPatient(Patient patient);
  void HCSGetRoles();
}
