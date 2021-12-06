package HCS.server.model;

import HCS.shared.utility.Subject;

public interface LoginModelServer extends Subject

{
  String Login(String username,String password);

}
