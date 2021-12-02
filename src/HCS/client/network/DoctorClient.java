package HCS.client.network;

import HCS.shared.utility.Subject;

public interface DoctorClient extends Subject
{
  void startClient();
  void HCSGetBookings();
}
