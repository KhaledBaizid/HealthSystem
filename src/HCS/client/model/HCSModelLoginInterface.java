package HCS.client.model;

import HCS.shared.utility.Subject;

public interface HCSModelLoginInterface extends Subject
{
  void HCSLogin(String username,String password);
}
