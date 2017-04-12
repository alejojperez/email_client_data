package com.gitlab.ultimate_jet_vacations.desktop_client.data.connections;

import org.javalite.activejdbc.DB;

public class GmailConnection implements IConnection
{
    /**
     * Connection name
     */
    protected static String connectionIdentifier = "credentials.sqlite";

    /**
     * Singleton instance
     */
    private static GmailConnection singleton = new GmailConnection( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private GmailConnection() { }

    /* Static 'instance' method */
    public static GmailConnection getInstance( ) {
        return singleton;
    }

    /**
     * Close the connection
     */
    public void close()
    {
        new DB(connectionIdentifier).close();
    }

    /**
     * Open the connection
     */
    public void open()
    {
        new DB(connectionIdentifier).open("org.sqlite.JDBC", "jdbc:sqlite:" + getClass().getResource("/database/" + connectionIdentifier).getPath(), "fakeUser", "fakePassword");
    }
}
