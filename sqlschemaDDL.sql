drop table bankuser cascade constraints;
drop table employee cascade constraints;
drop table customer cascade constraints;
drop table account cascade constraints;
drop table transaction cascade constraints;
drop table account_customer cascade constraints;
drop table inquiry cascade constraints;
drop table dispute cascade constraints;
drop table credit_request cascade constraints;
drop table credit_score cascade constraints;


drop sequence USER_SEQ;
drop sequence EMPLOYEE_SEQ;
drop sequence CUSTOMER_SEQ;
drop sequence ACCOUNT_SEQ;
drop sequence TRANSACTION_SEQ;
drop sequence ACCOUNT_CUSTOMER_SEQ;
drop sequence INQUIRY_SEQ;
drop sequence DISPUTE_SEQ;
drop sequence CREDIT_REQUEST_SEQ;
drop sequence CREDIT_SCORE_SEQ;


create sequence USER_SEQ;
create sequence EMPLOYEE_SEQ;
create sequence CUSTOMER_SEQ;
create sequence ACCOUNT_SEQ;
create sequence TRANSACTION_SEQ;
create sequence ACCOUNT_CUSTOMER_SEQ;
create sequence INQUIRY_SEQ;
create sequence DISPUTE_SEQ;
create sequence CREDIT_REQUEST_SEQ;
create sequence CREDIT_SCORE_SEQ;



create table bankuser(
    user_id number(20) primary key,
    username varchar2(30) not null unique,
    user_password varchar2(30) not null,
    first_name varchar2(30) not null,
    last_name varchar2(30) not null,
    email varchar2(30),
    phone varchar2(20) not null,
    address varchar2(200) not null,
    city varchar2(50) not null,
    add_state varchar2(50) not null,
    country varchar2(100) not null,
    zip varchar2(100) not null
);


create table employee(
    employee_id number(20) primary key,
    user_id number(20) not null,
    manager_id number(20) default null,
    employee_type varchar2(30) not null,
    constraint fk_employee_user foreign key (user_id) references bankuser(user_id)
);

create table customer(
    customer_id number(20) primary key,
    user_id number(20) not null,
    account_type varchar(30) default 'TEMP',
    constraint fk_customer_user foreign key (user_id) references bankuser(user_id)

);

create table account(
    account_id number(20) primary key,
    account_type varchar2(30) not null,
    balance number(20,2) default 0,
    date_opened date not null,
    date_closed date,
    customer_id number(20) not null,
    constraint fk_account_customer foreign key (customer_id) references customer(customer_id)
);

create table transaction(
    transaction_id number(20) primary key,
    account_id number(20) not null,
    creation_date date not null,
    transaction_balance number(20,2) not null,
    transaction_name varchar2(100) not null,
    constraint fk_transaction_account foreign key (account_id) references account(account_id)
);

create table account_customer(
    account_id number(20) not null,
    customer_id number(20) not null,
    primary key (account_id, customer_id),
    constraint fk_account_table foreign key (account_id) references account(account_id),
    constraint fk_customer_table foreign key (customer_id) references customer(customer_id)
);

create table inquiry(
    inquiry_id number(20) primary key,
    employee_id number(20) not null,
    customer_id number(20) not null,
    status varchar2(50),
    question varchar2(1000),
    constraint fk_inquiry_employee foreign key (employee_id) references employee(employee_id),
    constraint fk_inquiry_customer foreign key (customer_id) references customer(customer_id)
);


create table dispute(
    dispute_id number(20) primary key,
    customer_id number(20) not null,
    transaction_id number(20) not null,
    employee_id number(20) default 0, -- approverId
    comments varchar2(500),
    status varchar2(20) default 'PENDING',
    constraint fk_dispute_customer foreign key (customer_id) references customer(customer_id),
    constraint fk_dispute_transaction foreign key (transaction_id) references transaction(transaction_id),
    constraint fk_dispute_approver foreign key (employee_id) references employee(employee_id)
);

create table credit_request(
    credit_request_id number(20) primary key,
    customer_id number(20) not null,
    credit_apr number(20) default 0,
    credit_max number(20) not null,
    employee_id number(20) default null, -- approverId. null = anyone loan officer can approve it.
    status varchar(20) default 'PENDING',
    constraint fk_credit_request_customer foreign key (customer_id) references customer(customer_id)
    --constraint fk_credit_request_employee foreign key (employee_id) references employee(employee_id)
);

create table credit_score(
    credit_score_id number(20) primary key,
    customer_id number(20) not null,
    credit_score_estimation number(20) default 650,
    constraint fk_credit_score_customer foreign key (customer_id) references customer(customer_id)
);

commit;