

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'test','test','Bobby','McGiggleCainMcGee','giggles@example.com','194-229-3919','431 Fakers Blvd','Kansas City','Kansas','United States','66012');

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'officer','test','Konnichiwa','Man','manager@example.com','155-529-3991','2 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'manager','test','Johnny','Bravo','ohmomma@example.com','199-339-3611','76 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (USER_SEQ.nextVal,'rep','test','Dennis','Parkuli','d.park@example.com','137-298-5591','75 Fig Newtons Pkwy','Kansas City','Kansas','United States','66043');
    
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

insert into employee (employee_id, user_id, employee_type, manager_id) values(EMPLOYEE_SEQ.nextVal,2,'LOAN OFFICER', 2);
insert into employee (employee_id, user_id, employee_type) values(EMPLOYEE_SEQ.nextVal,3,'MANAGER');
insert into employee (employee_id, user_id, employee_type, manager_id) values(EMPLOYEE_SEQ.nextVal,4,'CUST REP', 2);
insert into employee (employee_id, user_id, employee_type, manager_id) values(EMPLOYEE_SEQ.nextVal,6,'LOAN OFFICER', 2);

insert into credit_request(credit_request_id,customer_id,credit_apr,credit_max,employee_id)
    values(CREDIT_REQUEST_SEQ.nextVal,1,5,5,null);
    
insert into account values (ACCOUNT_SEQ.nextVal, 'CHECKING', 1000, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
insert into account values (ACCOUNT_SEQ.nextVal, 'CHECKING', 900, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
    
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -10.54, 'Corner Store - Candy');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/06/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -15.45, 'Corner Store - Soda');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -20.54, 'Corner Store - Water');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/05/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -18.54, 'Corner Store - Milk');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/06/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -16.54, 'Corner Store - Cheetos');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -2.54, 'Corner Store - Cereal');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/08/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -5.54, 'Corner Store - Orange Juice');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/06/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -1.54, 'Corner Store - Bread');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -0.54, 'Corner Store - Gum');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/08/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -3.54, 'Corner Store - Donut');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/09/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -4.54, 'Corner Store - Hot dog');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/10/05 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -2.19, 'Corner Store - Eggs');


insert into transaction values (TRANSACTION_SEQ.nextVal, 2, TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), -18.60, 'Corner Store - Milk');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/03 17:00:44', 'yyyy/mm/dd hh24:mi:ss'), 25.00, 'Green Bank ATM');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/03 13:05:44', 'yyyy/mm/dd hh24:mi:ss'), 100.00, 'Green Bank ATM');
insert into transaction values (TRANSACTION_SEQ.nextVal, 1, TO_DATE('2019/07/03 13:05:44', 'yyyy/mm/dd hh24:mi:ss'), 250.00, 'Green Bank ATM');    

--insert into dispute values (DISPUTE_SEQ.nextVal, 1, 1, 3, 'lol', 'REJECTED');
--insert into dispute values (DISPUTE_SEQ.nextVal, 1, 2, 3, 'lmao', 'APPROVED');

--insert into dispute values (DISPUTE_SEQ.nextVal, 1, 1, 3, 'lol', 'REJECTED');
--insert into dispute values (DISPUTE_SEQ.nextVal, 1, 2, 3, 'lmao', 'APPROVED');
commit;