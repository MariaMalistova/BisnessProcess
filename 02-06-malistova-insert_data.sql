INSERT ALL 
     INTO user_groups (user_group_id, group_name) VALUES (1, 'Administrator')
     INTO user_groups (user_group_id, group_name) VALUES (2, 'Moderator')
     INTO user_groups (user_group_id, group_name) VALUES (3, 'Author')
     INTO user_groups (user_group_id, group_name) VALUES (4, 'Editor')
     INTO user_groups (user_group_id, group_name) VALUES (5, 'Corrector')
SELECT * FROM DUAL;

INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 1, 'admin','admin', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 2, 'moder1','moder1', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 2, 'moder2','moder2', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 3, 'author1','author1', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 3, 'author2','author2', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 3, 'author3','author3', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 4, 'editor1','editor1', 1);    
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 4, 'editor2','editor2', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 4, 'editor3','editor3', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 5, 'corrector1','corrector1', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 5, 'corrector2','corrector2', 1);
INSERT INTO users (user_id, user_role, login, user_password, has_access) VALUES (DEFAULT, 5, 'corrector3','corrector3', 1);

INSERT ALL 
     INTO steps (step_id, step_role) VALUES (1, 'Assigning')
     INTO steps (step_id, step_role) VALUES (2, 'Reading')
     INTO steps (step_id, step_role) VALUES (3, 'Editing')
     INTO steps (step_id, step_role) VALUES (4, 'Correcting')
     INTO steps (step_id, step_role) VALUES (5, 'Ready')
SELECT * FROM DUAL;

INSERT ALL 
     INTO article_access (access_id, user_role, step) VALUES (1, 1, 1)
     INTO article_access (access_id, user_role, step) VALUES (2, 2, 2)
     INTO article_access (access_id, user_role, step) VALUES (3, 3, 5)
     INTO article_access (access_id, user_role, step) VALUES (4, 4, 3)
     INTO article_access (access_id, user_role, step) VALUES (5, 5, 4)
SELECT * FROM DUAL;

select * from users