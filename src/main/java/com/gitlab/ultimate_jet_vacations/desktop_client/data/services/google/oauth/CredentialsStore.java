package com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.oauth;

import com.gitlab.ultimate_jet_vacations.desktop_client.data.connections.GmailConnection;
import com.gitlab.ultimate_jet_vacations.desktop_client.data.entities.GmailAccountCredential;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialStore;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;

public class CredentialsStore implements CredentialStore
{
    /**
     * Load Credentials
     * @param emailAccount
     * @param credential
     * @return
     * @throws IOException
     */
    public boolean load(String emailAccount, Credential credential) throws IOException
    {
        GmailConnection.getInstance().open();

        GmailAccountCredential accountCredential = GmailAccountCredential.findFirst("identifier = ?", emailAccount);

        GmailConnection.getInstance().close();

        if(accountCredential == null)
            return false;

        try
        {
            JsonReader jsonReader = Json.createReader(new StringReader((String)accountCredential.get("credentials")));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            if(jsonObject.getString("access_token") != null)
                credential.setAccessToken(jsonObject.getString("access_token"));

            if(jsonObject.getString("refresh_token") != null)
                credential.setRefreshToken(jsonObject.getString("refresh_token"));

            Long expiresAt = jsonObject.getJsonNumber("expires_at").longValue();
            if(expiresAt > 0)
                credential.setExpirationTimeMilliseconds(expiresAt);
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * Save credentials
     * @param emailAccount
     * @param credential
     * @throws IOException
     */
    public void store(String emailAccount, Credential credential) throws IOException
    {
        JsonObjectBuilder credentialsBuilder = Json.createObjectBuilder();

        String accessToken = credential.getAccessToken();
        String refreshToken = credential.getRefreshToken();
        Long expiresAt = credential.getExpirationTimeMilliseconds();

        if(accessToken != null) credentialsBuilder.add("access_token", accessToken);
        if(refreshToken != null) credentialsBuilder.add("refresh_token", refreshToken);
        if(expiresAt != null) credentialsBuilder.add("expires_at", expiresAt);

        String credentials = credentialsBuilder.build().toString();

        GmailConnection.getInstance().open();

        GmailAccountCredential accountCredential = GmailAccountCredential.findFirst("identifier = ?", emailAccount);

        if(accountCredential == null)
            accountCredential = new GmailAccountCredential();

        if (accountCredential.get("alias") == null)
            accountCredential.set("alias", emailAccount);

        accountCredential.set("identifier", emailAccount);
        accountCredential.set("credentials", credentials);

        accountCredential.saveIt();

        GmailConnection.getInstance().close();
    }

    /**
     * Delete stored credentials
     * @param emailAccount
     * @param credential
     * @throws IOException
     */
    public void delete(String emailAccount, Credential credential) throws IOException
    {
        GmailConnection.getInstance().open();

        GmailAccountCredential.delete("identifier = ?", emailAccount);

        GmailConnection.getInstance().close();
    }
}
