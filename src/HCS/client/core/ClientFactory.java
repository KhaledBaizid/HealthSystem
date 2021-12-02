package HCS.client.core;

import HCS.client.network.*;
/////import chat.client.network.Client;

public class ClientFactory
{
    private AdminClient client;
    private ReceptionClient clientReception;
    private LoginClient clientLogin;
    private DoctorClient clientDoctor;

    public AdminClient getClient()
    {
        if (client == null) client = new AdminClientImpl();
        return client;
    }

    //
    public ReceptionClient getClientReception()
    {
        if (clientReception == null) clientReception = new ReceptionClientImpl();
        return clientReception;
    }
    //
    public LoginClient getClientLogin()
    {
        if (clientLogin==null) clientLogin=new LoginClientImpl();
        return clientLogin;
    }
    //
    public DoctorClient getClientDoctor()
    {
        if (clientDoctor==null)clientDoctor=new DoctorClientImpl();
        return clientDoctor;
    }
}
