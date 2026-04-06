DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Trip;
DROP TABLE IF EXISTS Climate;

PRAGMA foreign_keys = ON;

CREATE TABLE User(
  userID INTEGER PRIMARY KEY,
  username VARCHAR(255) NOT NULL
);

CREATE TABLE Location(
  locationID INTEGER PRIMARY KEY,
  latitude REAL NOT NULL,
  longitude REAL NOT NULL,
  lastUpdate INT,
  displayName VARCHAR(50) NOT NULL
);

CREATE TABLE Trip(
  tripID INTEGER PRIMARY KEY,
  userID INTEGER NOT NULL,
  locationID INTEGER NOT NULL,
  startDate INT NOT NULL,
  endDate INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY(userID) REFERENCES User(userID),
  FOREIGN KEY(locationID) REFERENCES Location(locationID)
);

CREATE TABLE Climate (
  date INT,
  locationID INTEGER NOT NULL,
  maxTemp REAL,
  minTemp REAL,
  climateCode INT,
  precipitationChance INT,
  PRIMARY KEY(date, locationID),
  FOREIGN KEY(locationID) REFERENCES Location(locationID) ON DELETE CASCADE
);
