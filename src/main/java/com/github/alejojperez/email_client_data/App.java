package com.github.alejojperez.email_client_data;

import com.github.alejojperez.email_client_data.services.google.gmail.GmailManager;
import com.github.alejojperez.email_client_data.services.google.oauth.Authorizer;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;

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
            System.out.println("Labels: ");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }

        Profile profile = gmailService.users().getProfile(user).execute();
        System.out.printf("Email: %s", profile.getEmailAddress());
    }
}