package com.github.alejojperez.email_client_data.services.google.gmail;

import com.github.alejojperez.email_client_data.services.google.configuration.GlobalValues;
import com.github.alejojperez.email_client_data.services.google.Manager;
import com.github.alejojperez.email_client_data.services.google.oauth.Authorizer;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.gmail.Gmail;
import java.io.IOException;

public class GmailManager implements Manager
{
    /**
     * Singleton instance
     */
    private static GmailManager singleton = new GmailManager();

    /**
     * A private Constructor prevents any other
     * class from instantiating.
     */
    private GmailManager() { }

    /**
     * Static 'instance' method
     * @return
     */
    public static GmailManager getInstance( ) {
        return singleton;
    }

    /**
     * Get the Gmail service
     * @param accountIdentifier
     * @return
     */
    @Override
    public Gmail getService(String accountIdentifier) throws IOException
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
    public Gmail getService(Credential credential) throws IOException
    {
        return new Gmail
                .Builder(GlobalValues.HTTP_TRANSPORT, GlobalValues.JSON_FACTORY, credential)
                .setApplicationName(GlobalValues.APPLICATION_NAME)
                .build();
    }
}