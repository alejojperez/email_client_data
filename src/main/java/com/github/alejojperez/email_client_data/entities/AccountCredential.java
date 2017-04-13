package com.github.alejojperez.email_client_data.entities;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

@DbName("credentials.sqlite")
@Table("account_credentials")
public class AccountCredential extends Model { }
