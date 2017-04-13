package com.github.alejojperez.email_client_data.connections;

import org.javalite.activejdbc.DB;

public class CredentialsConnection implements IConnection
{
    /**
     * Connection name
     */
    protected static String connectionIdentifier = "credentials.sqlite";

    /**
     * Singleton instance
     */
    private static CredentialsConnection singleton = new CredentialsConnection( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private CredentialsConnection() { }

    /* Static 'instance' method */
    public static CredentialsConnection getInstance( ) {
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
