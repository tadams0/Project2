 --Insert into bankuser table
 insert into bankuser values (1, 'test', 'test', 'John', 'Doe', 'test@email.com', '623-326-6623',
    '123 st', 'cityville', 'az', 'usa', '12345');   
insert into customer values (1, 1);


--Insert into account table
insert into account values (1, 'checkings', 1000, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2005/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1);
    
commit;