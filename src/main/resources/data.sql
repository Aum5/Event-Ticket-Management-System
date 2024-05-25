INSERT INTO ticket (name, email, gender, number, price, pay) VALUES
('Jon','eagle@gmail.com','Male', '1234567896','$35(Regular Ticket)','CreditCard'),
('Aum','xyz@gmail.com','Male', '1234567896','$35(Regular Ticket)','CreditCard'),
('Jeenitesh','abc@gmail.com','Male', '1234567896','$60(VIP Ticket)','PayPal'),
('Aryan','fgw@gmail.com','Female', '1234567896','$35(Regular Ticket)','CreditCard'),
('Viraj','rwhg@gmail.com','Prefer Not To Say', '1234567896','$60(VIP Ticket)','PayPal'),
('Devarsh','ved@gmail.com','Prefer Not To Say', '1234567896','$20(Student Ticket)','CreditCard'),
('Ishan','sg@gmail.com','Female', '1234567896','$20(Student Ticket)','PayPal');

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Aum', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jeenitesh', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Aryan', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Viraj', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Devarsh', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Ishan', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

 
insert into sec_role (roleName)
values ('ROLE_VENDER');

insert into sec_role (roleName)
values ('ROLE_GUEST');

  
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);

insert into user_role (userId, roleId)
values (7, 2);