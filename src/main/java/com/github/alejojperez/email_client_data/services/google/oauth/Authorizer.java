package com.github.alejojperez.email_client_data.services.google.oauth;

import com.github.alejojperez.email_client_data.services.google.configuration.GlobalValues;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Authorizer
{
    public static String clientCredentialsPath = "/credentials/client_secret.json";

    /**
     * Obtain the credentials for an specific email account
     * @param accountEmail
     * @return
     * @throws IOException
     */
    public static Credential getCredentials(String accountEmail) throws IOException
    {
        // Load client secrets.
        InputStream in = Authorizer.class.getResourceAsStream(clientCredentialsPath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GlobalValues.JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(GlobalValues.HTTP_TRANSPORT, GlobalValues.JSON_FACTORY, clientSecrets, GlobalValues.SCOPES)
                .setCredentialStore(new CredentialsStore())
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(accountEmail);
    }
}
