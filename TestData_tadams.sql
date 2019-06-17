

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (1,'test','test','Bobby','McGiggleCainMcGee','giggles@example.com','194-229-3919','431 Fakers Blvd','Kansas City','Kansas','United States','66012');

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (2,'manager','test','Manager','Man','manager@example.com','155-529-3991','2 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (3,'test2','test','Johnny','Bravo','ohmomma@example.com','199-339-3611','76 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (4,'test3','test','Dennis','Parkuli','d.park@example.com','137-298-5591','75 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (5,'customer1','test','Karry','Pachinkle','pachin@example.com','166-3994-2222','19 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (6,'test1','test','Erik','Bronn','e.bronn@example.com','144-427-8716','77 Fig Drive','Kansas City','Kansas','United States','66044');
    

insert into customer (customer_id, user_id) values (1,1);
insert into customer (customer_id, user_id) values (2,5);

insert into employee (employee_id, user_id, employee_type) values(1,2,'LOAN OFFICER');
insert into employee (employee_id, user_id, employee_type) values(2,3,'MANAGER');
insert into employee (employee_id, user_id, employee_type) values(3,4,'CUST REP');
insert into employee (employee_id, user_id, employee_type, manager_id) values(4,6,'LOAN OFFICER', 2);

insert into credit_request(credit_request_id,customer_id,credit_apr,credit_max,employee_id)
    values(1,1,5,5,null);
    
    
insert into account values (1, 'CHECKING', 1000, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
    
    
commit;