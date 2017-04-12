package com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.plus.PlusScopes;

import java.util.Arrays;
import java.util.List;

public class GlobalValues
{
    /**
     * Application Name
     */
    public static final String APPLICATION_NAME = "AdminUJV";

    /**
     * Global instance of the JSON factory
     */
    public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this application
     */
    public static final List<String> SCOPES = Arrays.asList(
            GmailScopes.MAIL_GOOGLE_COM,
            GmailScopes.GMAIL_COMPOSE,
            GmailScopes.GMAIL_INSERT,
            GmailScopes.GMAIL_LABELS,
            GmailScopes.GMAIL_METADATA,
            GmailScopes.GMAIL_MODIFY,
            GmailScopes.GMAIL_READONLY,
            GmailScopes.GMAIL_SEND,
            GmailScopes.GMAIL_SETTINGS_BASIC,
            GmailScopes.GMAIL_SETTINGS_SHARING,
            PlusScopes.PLUS_LOGIN,
            PlusScopes.PLUS_ME,
            PlusScopes.USERINFO_EMAIL,
            PlusScopes.USERINFO_PROFILE
    );

    /**
     * Global instance of the HTTP transport
     */
    public static HttpTransport HTTP_TRANSPORT;

    /**
     * Initialize the HTTP Transport
     */
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
