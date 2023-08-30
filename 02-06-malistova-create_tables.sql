--create table users that contains information about app's users 
CREATE TABLE users (
   user_id INTEGER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
   user_role INTEGER,
   login VARCHAR2(50),
   user_password VARCHAR2(50),
   has_access NUMBER(1),
   CONSTRAINT login_unique UNIQUE (login));

--create table user_groups that contains information about users' groups    
CREATE TABLE user_groups (
   user_group_id INTEGER,
   group_name VARCHAR2(30),
   CONSTRAINT group_name_unique UNIQUE (group_name));

--create table article that contains information about articles  
CREATE TABLE article (
   article_id INTEGER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
   author INTEGER,
   text LONG,
   step INTEGER,
   moderator INTEGER,
   editor INTEGER,
   corrector INTEGER);

--create table article_access that contains information about which user groups have an access to articles at which step
CREATE TABLE article_access (
   access_id INTEGER,
   user_role INTEGER,
   step INTEGER);

--create table steps that contains information about steps that articles have   
CREATE TABLE steps (
   step_id INTEGER,
   step_role VARCHAR2(20),
  CONSTRAINT step_role_unique UNIQUE (step_role));   

--add primary key to the table users
ALTER TABLE users ADD (
  CONSTRAINT users_pk PRIMARY KEY (user_id));

--add primary key to the table user_groups
ALTER TABLE user_groups ADD (
  CONSTRAINT user_groups_pk PRIMARY KEY (user_group_id));

--add primary key to the table article
ALTER TABLE article ADD (
  CONSTRAINT article_pk PRIMARY KEY (article_id));

--add primary key to the table article_access 
ALTER TABLE article_access ADD (
  CONSTRAINT article_access_pk PRIMARY KEY (access_id, user_role));

--add primary key to the table steps    
ALTER TABLE steps ADD (
  CONSTRAINT step_pk PRIMARY KEY (step_id));

--add foreign key on the table users to the table user_groups
ALTER TABLE users
ADD FOREIGN KEY (user_role) REFERENCES user_groups(user_group_id);

--add foreign key on the table article_access to the table user_groups
ALTER TABLE article_access
ADD FOREIGN KEY (user_role) REFERENCES user_groups(user_group_id);

--add foreign key on the table article_access to the table steps
ALTER TABLE article_access
ADD FOREIGN KEY (step) REFERENCES steps(step_id);

--add foreign key on the table article to the table users
ALTER TABLE article
ADD FOREIGN KEY (step) REFERENCES users(user_id);

--add foreign key on the table article to the table users
ALTER TABLE article
ADD FOREIGN KEY (author) REFERENCES users(user_id);

--add foreign key on the table article to the table users
ALTER TABLE article
ADD FOREIGN KEY (moderator) REFERENCES users(user_id);

--add foreign key on the table article to the table users
ALTER TABLE article
ADD FOREIGN KEY (editor) REFERENCES users(user_id);

--add foreign key on the table article to the table users
ALTER TABLE article
ADD FOREIGN KEY (corrector) REFERENCES users(user_id);