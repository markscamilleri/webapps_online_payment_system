INSERT INTO UserGroup(name) VALUES ('user');
INSERT INTO UserGroup(name) VALUES ('admin');

INSERT INTO AdminUser(username, email, encryptedPassword, registrationTimestamp, userGroup) VALUES ('admin1', 'admin@example.com', '25F43B1486AD95A1398E3EEB3D83BC4010015FCC9BEDB35B432E00298D5021F7', CURRENT_TIMESTAMP, (SELECT id FROM UserGroup WHERE name = 'admin')); 
