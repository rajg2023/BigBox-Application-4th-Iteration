-- create and select the database
DROP DATABASE IF EXISTS bigboxdb;
CREATE DATABASE bigboxdb;
USE bigboxdb;

-- create the Divisions table
CREATE TABLE division (
  ID		     INT            PRIMARY KEY  AUTO_INCREMENT,
  DivisionNumber VARCHAR(3)     NOT NULL,
  Name           VARCHAR(255)   NOT NULL,
  Address        VARCHAR(255)   NOT NULL,
  City           VARCHAR(255)   NOT NULL,
  State    		 VARCHAR(2)     NOT NULL,
  ZipCode		 VARCHAR(5)     NOT NULL
);

-- create the Stores table
CREATE TABLE store (
  StoreID	     INT            PRIMARY KEY  AUTO_INCREMENT,
  DivisionNumber VARCHAR(3)     NOT NULL,
  StoreNumber    VARCHAR(5)     NOT NULL,
  Sales          DECIMAL(10,2)  NOT NULL,
  Name           VARCHAR(255)   NOT NULL,
  Address        VARCHAR(255)   NOT NULL,
  City           VARCHAR(255)   NOT NULL,
  State    		 VARCHAR(2)     NOT NULL,
  ZipCode		 VARCHAR(5)     NOT NULL
);


-- insert some rows into the Stores table
INSERT INTO division VALUES 
(1, '001', 'Cincinnati Division Office', '2200 Fields Ertel Rd.', 'Mason', 'OH', '45249'),
(2, '111', 'Louisville Division Office', '4000 Liberty St.', 'Louisville', 'KY', '40204');

-- insert some rows into the Stores table
INSERT INTO store VALUES 
(1, '001', '00011', 3445555.89, 'Mason Big Box', '5711 Fields Ertel Rd.', 'Mason', 'OH', '45249'),
(2, '001', '00255', 3444433.33, 'Downtown Big Box','9330 Main St.', 'Cincinnati', 'OH', '45249'),
(3, '001', '00172', 4555543.66, 'Goshen Big Box','6777 Goshen Rd.', 'Goshen', 'OH', '45249'),
(4, '001', '00075', 3444555.99, 'Bridgetown Big Box','3888 Race Rd.', 'Cincinnati', 'OH', '45249'),
(5, '111', '00011', 7668944.33, 'Louisville Big Box','1111 Washington St.', 'Louisville', 'KY', '40206'),
(6, '111', '00044', 8777655.33, 'Riverfront Big Box','8000 Liberty St.', 'Louisville', 'KY', '40204');

-- create a user and grant privileges to that user
GRANT SELECT, INSERT, DELETE, UPDATE
ON bigboxdb.*
TO bigboxdb_user@localhost
IDENTIFIED BY 'sesame';