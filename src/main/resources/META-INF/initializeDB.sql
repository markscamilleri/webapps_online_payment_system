INSERT INTO UserGroup(id, name) VALUES (1, 'user');
INSERT INTO UserGroup(id, name) VALUES (2, 'admin');

INSERT INTO SystemUser(id, username, email, encryptedPassword, registrationTimestamp, userGroupId) VALUES (1, 'admin1', 'admin@example.com', '25F43B1486AD95A1398E3EEB3D83BC4010015FCC9BEDB35B432E00298D5021F7', CURRENT_TIMESTAMP, 2); 
