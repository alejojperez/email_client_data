package com.github.alejojperez.email_client_data.services.google.plus;

import com.github.alejojperez.email_client_data.services.google.configuration.GlobalValues;
import com.github.alejojperez.email_client_data.services.google.oauth.Authorizer;
import com.github.alejojperez.email_client_data.services.google.Manager;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.plus.Plus;

import java.io.IOException;

public class PlusManager implements Manager
{
    /**
     * Singleton instance
     */
    private static PlusManager singleton = new PlusManager();

    /**
     * A private Constructor prevents any other
     * class from instantiating.
     */
    private PlusManager() { }

    /**
     * Static 'instance' method
     * @return
     */
    public static PlusManager getInstance( ) { return singleton; }

    /**
     * Get the Gmail service
     * @param accountIdentifier
     * @return
     */
    @Override
    public Plus getService(String accountIdentifier) throws IOException
    {
        Credential credential = Authorizer.getCredentials(accountIdentifier);

        return this.getService(credential);
    }

    /**
     * Get the Gmail service
     * @param credential
     * @return
     */
    @Override
    public Plus getService(Credential credential) throws IOException
    {
        return new Plus
                .Builder(GlobalValues.HTTP_TRANSPORT, GlobalValues.JSON_FACTORY, credential)
                .setApplicationName(GlobalValues.APPLICATION_NAME)
                .build();
    }
}
