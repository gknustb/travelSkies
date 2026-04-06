DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS trip;
DROP TABLE IF EXISTS climate;

PRAGMA foreign_keys = ON;

CREATE TABLE user(
  userID INTEGER PRIMARY KEY,
  username VARCHAR(255) NOT NULL
);

CREATE TABLE location(
  locationID INTEGER PRIMARY KEY,
  latitude REAL NOT NULL,
  longitude REAL NOT NULL,
  lastUpdate INT,
  displayName VARCHAR(50) NOT NULL
);

CREATE TABLE trip(
  tripID INTEGER PRIMARY KEY,
  userID INTEGER NOT NULL,
  locationID INTEGER NOT NULL,
  startDate INT NOT NULL,
  endDate INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY(userID) REFERENCES user(userID),
  FOREIGN KEY(locationID) REFERENCES location(locationID)
);

CREATE TABLE climate (
  date INT,
  locationID INTEGER NOT NULL,
  maxTemp REAL,
  minTemp REAL,
  climateCode INT,
  precipitationChance INT,
  PRIMARY KEY(date, locationID),
  FOREIGN KEY(locationID) REFERENCES location(locationID) ON DELETE CASCADE
);
