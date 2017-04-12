package com.gitlab.ultimate_jet_vacations.desktop_client.data.entities;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

@DbName("credentials.sqlite")
@Table("account_credentials")
public class GmailAccountCredential extends Model { }
