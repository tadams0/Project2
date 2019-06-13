

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (1,'test','test','Bobby','McGiggleCainMcGee','giggles@example.com','194-229-3919','431 Fakers Blvd','Kansas City','Kansas','United States','66012');

insert into bankuser (user_id, username, user_password, first_name, last_name, email, phone, address, city, add_state, country, zip)
    values (2,'test1','test1','Erik','Bronn','e.bronn@example.com','144-427-8716','77 Fig Drive','Kansas City','Kansas','United States','66044');

insert into customer (customer_id, user_id) values (1,1);

insert into employee (employee_id, user_id, employee_type) values(1,2,'LOAN OFFICER');

insert into credit_request(credit_request_id,customer_id,credit_apr,credit_max,employee_id)
    values(1,1,5,5,null);
    
commit;