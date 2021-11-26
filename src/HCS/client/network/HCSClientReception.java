package HCS.client.network;

import HCS.shared.utility.Subject;

public interface HCSClientReception extends Subject
{
  void startClient();
  void createPatient(String cprnumber,String firstname,String Lastname);
  void   HCSGetRoles();
}
