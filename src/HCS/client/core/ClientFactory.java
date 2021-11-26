package HCS.client.core;

import HCS.client.network.*;
/////import chat.client.network.Client;

public class ClientFactory
{
    private HCSClientAdmin client;
    private HCSClientReception clientReception;
    private HCSClientLogin clientLogin;
    private HCSClientDoctor clientDoctor;

    public HCSClientAdmin getClient()
    {
        if (client == null) client = new RMIClientAdmin();
        return client;
    }

    //
    public HCSClientReception getClientReception()
    {
        if (clientReception == null) clientReception = new RMIClientReception();
        return clientReception;
    }
    //
    public HCSClientLogin getClientLogin()
    {
        if (clientLogin==null) clientLogin=new RMICientLogin();
        return clientLogin;
    }
    //
    public HCSClientDoctor getClientDoctor()
    {
        if (clientDoctor==null)clientDoctor=new RMIClientDoctor();
        return clientDoctor;
    }
}
