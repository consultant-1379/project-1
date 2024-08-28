--For Test Setup Only
CREATE TABLE IF NOT EXISTS Teams (
	teamId INT NOT NULL AUTO_INCREMENT,
	teamName VARCHAR(25),
	teamMembers TEXT(100) NOT NULL,
	retrospectiveId INT,
	PRIMARY KEY (teamId)
);