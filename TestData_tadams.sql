

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'test','test','Bobby','McGiggleCainMcGee','giggles@example.com','194-229-3919','431 Fakers Blvd','Kansas City','Kansas','United States','66012');

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'manager','test','Manager','Man','manager@example.com','155-529-3991','2 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'test2','test','Johnny','Bravo','ohmomma@example.com','199-339-3611','76 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'test3','test','Dennis','Parkuli','d.park@example.com','137-298-5591','75 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'customer1','test','Karry','Pachinkle','pachin@example.com','166-3994-2222','19 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'test1','test','Erik','Bronn','e.bronn@example.com','144-427-8716','77 Fig Drive','Kansas City','Kansas','United States','66044');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'customer2','test','Yanny','Larel','yorl@example.com','199-999-9995','56 I give up Blvd','Kansas City','Kansas','United States','66001');
    

insert into customer (customer_id, user_id, account_type) values (CUSTOMER_SEQ.nextVal,1, 'PERM');
insert into customer (customer_id, user_id, account_type) values (CUSTOMER_SEQ.nextVal,5, 'PERM');
insert into customer (customer_id, user_id, account_type) values (CUSTOMER_SEQ.nextVal,7, 'PERM');

insert into credit_score (credit_score_id, customer_id, credit_score_estimation) values (CREDIT_SCORE_SEQ.nextVal, 1, 700);
insert into credit_score (credit_score_id, customer_id, credit_score_estimation) values (CREDIT_SCORE_SEQ.nextVal, 2, 400);
insert into credit_score (credit_score_id, customer_id, credit_score_estimation) values (CREDIT_SCORE_SEQ.nextVal, 3, 600);

insert into employee (employee_id, user_id, employee_type) values(EMPLOYEE_SEQ.nextVal,2,'LOAN OFFICER');
insert into employee (employee_id, user_id, employee_type) values(EMPLOYEE_SEQ.nextVal,3,'MANAGER');
insert into employee (employee_id, user_id, employee_type) values(EMPLOYEE_SEQ.nextVal,4,'CUST REP');
insert into employee (employee_id, user_id, employee_type, manager_id) values(EMPLOYEE_SEQ.nextVal,6,'LOAN OFFICER', 2);

insert into credit_request(credit_request_id,customer_id,credit_apr,credit_max,employee_id)
    values(CREDIT_REQUEST_SEQ.nextVal,1,5,5,null);
    
    
<<<<<<< HEAD
insert into account values (1, 'CHECKING', 1000, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
insert into account values (2, 'CHECKING', 900, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
    
insert into transaction values (1, 1, TO_DATE('2004/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 50, 'Corner Store - Candy');
insert into transaction values (2, 1, TO_DATE('2004/06/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 50, 'Corner Store - Soda');

insert into transaction values (3, 2, TO_DATE('2004/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 50, 'Corner Store - Milk');
    
=======
insert into account values (ACCOUNT_SEQ.nextVal, 'CHECKING', 1000, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:13:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
insert into account values (ACCOUNT_SEQ.nextVal, 'SAVINGS', 2000, TO_DATE('2003/05/08 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:55:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
insert into account values (ACCOUNT_SEQ.nextVal, 'CHECKING', 3000, TO_DATE('2003/05/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:20:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
insert into account values (ACCOUNT_SEQ.nextVal, 'CHECKING', 4000, TO_DATE('2003/05/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:10:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
>>>>>>> 9f347f394add47042d4815bba171bd35fe9274b8
    
commit;