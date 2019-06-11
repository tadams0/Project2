-- Fresh start. Drop the user.
drop user greenbank cascade;

-- create a user in the database
create user greenbank
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;

grant connect to greenbank;
grant resource to greenbank;
grant create session to greenbank;
grant create table to greenbank;
grant create view to greenbank;
