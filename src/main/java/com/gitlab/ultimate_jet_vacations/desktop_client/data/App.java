package com.gitlab.ultimate_jet_vacations.desktop_client.data;

import com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.gmail.GmailManager;
import com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.oauth.Authorizer;
import com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.oauth.CredentialsStore;
import com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google.plus.PlusManager;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main(String[] args) throws Exception
    {
        String user = "me";
        String account = "alejandro@ultimatejetvacations.com";
        Credential credential = Authorizer.getCredentials(account);

        // Build a new authorized API client service.
        Gmail gmailService = GmailManager.getInstance().getService(credential);

        // Print the labels in the user's account.
        ListLabelsResponse listResponse = gmailService.users().labels().list(user).execute();

        List<Label> labels = listResponse.getLabels();
        if (labels.size() == 0) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }

        Plus plusService = PlusManager.getInstance().getService(credential);
        Person userInfo = plusService.people().get("me").execute();

        List<Person.Emails> emails = userInfo.getEmails();
        if (emails.size() == 0) {
            System.out.println("No emails found.");
        } else {
            System.out.println("Emails:");
            for (Person.Emails email : emails) {
                System.out.printf("- %s\n", email.getValue());
            }
        }
    }
}