package HCS.client.network;

import HCS.shared.utility.Subject;

public interface LoginClient extends Subject
{
  void startClient();
  void HCSLogin(String username,String password);
}
