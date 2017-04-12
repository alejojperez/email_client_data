package com.gitlab.ultimate_jet_vacations.desktop_client.data.services.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;

import java.io.IOException;

public interface Manager
{
    static Manager getInstance() { return null; };
    AbstractGoogleJsonClient getService(String accountIdentifier) throws IOException;
    AbstractGoogleJsonClient getService(Credential credential) throws IOException;
}
