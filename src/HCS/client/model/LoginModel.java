package HCS.client.model;

import HCS.shared.utility.Subject;

public interface LoginModel extends Subject
{
  void HCSLogin(String username,String password);
}
