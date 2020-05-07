INSERT INTO UserGroup(name) ('user');
INSERT INTO UserGroup(name) ('admin');

INSERT INTO AdminUser(username, email, encryptedPassword, registrationTimestamp, userGroup) ('admin', 'admin@example.com', '$2a$08$fx0U//TuJoiyX0MtFg1d6Ok6iZHG3I3biZJtSLJDCo/SSTcf.3wKS', CURRENT_TIMESTAMP, (SELECT id FROM UserGroup WHERE name = 'admin')); 
