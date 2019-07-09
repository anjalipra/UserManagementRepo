 
INSERT INTO roles(name) VALUES('ROLE_ADMIN'), ('ROLE_USER');


INSERT INTO USERS (DATE_OF_BIRTH, EMAIL, FIRST_NAME, HOME_ADDRESS, LAST_NAME, 
OFFICE_ADDRESS, PASSWORD, USERNAME) VALUES
  (TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'def@qwe.com', 'Adam', 'home address', 'Apple', 'office address', '$2a$10$vgpxgNinUxxyg0RYhulK0OA/W52aJpR7Fu1L2inP3zdlAXClAq.I2', 'admin');


INSERT INTO user_roles(role_id, user_id) values (1, 1);
