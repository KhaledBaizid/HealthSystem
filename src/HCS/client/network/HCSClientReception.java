package HCS.client.network;

import HCS.shared.transferObjects.Patient;
import HCS.shared.utility.Subject;

public interface HCSClientReception extends Subject
{
  void startClient();
  void createPatient(Patient patient);
  void   HCSGetRoles();
}
