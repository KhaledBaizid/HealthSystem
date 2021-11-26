package HCS.client.model;

import HCS.shared.utility.Subject;

public interface HCSModelReceptionInterface extends Subject
{
  void createPatient(String cprnumber,String firstname,String Lastname);
  void HCSGetRoles();
}
