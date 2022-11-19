INSERT INTO ROLE (ID, NAME) VALUES(1,'ADMIN');
INSERT INTO ROLE (ID, NAME) VALUES(2,'TEACHER');

INSERT INTO USER (id, first_name, last_name, email, password, phone, address1, state, country, zip)
VALUES(1, 'Arvind','Chandok','arvind.chandok@gmail.com','password','2148362395','111 Morel','TX','USA','75002');
INSERT INTO USER_ROLES (user_id, roles_id) VALUES(1,1);