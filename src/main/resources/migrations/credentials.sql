CREATE TABLE account_credentials
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    alias VARCHAR NOT NULL,
    identifier VARCHAR NOT NULL,
    credentials TEXT NOT NULL
);
CREATE UNIQUE INDEX accounts_alias_uindex ON account_credentials (alias);
CREATE UNIQUE INDEX accounts_identifier_uindex ON account_credentials (identifier);