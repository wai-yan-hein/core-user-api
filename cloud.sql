alter table department
add column acc_queue varchar(255) null after inv_queue,
change column queue_name inv_queue varchar(255) null default null ;

set sql_safe_updates=0;
update menu
set menu_class ='Inventory'
where menu_class='Report';

alter table menu
add column comp_code varchar(15) not null after menu_code,
drop primary key,
add primary key (menu_code, comp_code),
drop index menu_id ;

alter table role_prop
add column comp_code varchar(15) not null after prop_key,
drop primary key,
add primary key (role_code, prop_key, comp_code);

alter table privilege_menu
add column comp_code varchar(15) not null after role_code,
drop primary key,
add primary key (menu_code, role_code, comp_code);

set sql_safe_updates=0;
update privilege_menu
set comp_code ='01';
update menu
set comp_code ='01';
update role_prop
set comp_code ='01';
delete from role where role_name='';

alter table company_info
add column created_by varchar(15) null after currency,
add column created_date timestamp null after created_by,
add column bus_id  int null after  created_date ,
add column batch_lock bit(1) not null default 0 after bus_id,
add column year_end_date date null after batch_lock;
drop view if exists v_role_company;
create  view v_role_company as select p.role_code as role_code,p.comp_code as comp_code,p.allow as allow,com.name as name,com.phone as phone,com.address as address,com.start_date as start_date,com.end_date as end_date,com.currency as currency,com.batch_lock as batch_lock,com.year_end_date as year_end_date from (privilege_company p join company_info com on(p.comp_code = com.comp_code));

create table business_type (
  bus_id int(11) not null,
  bus_name varchar(255) not null,
  primary key (bus_id)
) engine=innodb default charset=utf8mb3 collate=utf8mb3_general_ci;

create table menu_template (
  menu_id int(15) not null,
  bus_id int(15) not null,
  menu_class varchar(150) default null,
  menu_name varchar(50) default null,
  menu_name_mm varchar(500) default null,
  menu_url varchar(500) default null,
  parent_menu_id int(11) not null,
  menu_type varchar(50) default null,
  account varchar(15) default null,
  order_by int(11) default null,
  primary key (menu_id,bus_id)
) engine=innodb default charset=utf8mb3 collate=utf8mb3_general_ci;

create table project (
  project_no varchar(15) not null,
  comp_code varchar(15) not null,
  project_name varchar(255) not null,
  start_date date not null,
  end_date date not null,
  budget double default null,
  project_status varchar(15) not null,
  primary key (project_no,comp_code)
) engine=innodb default charset=utf8mb3 collate=utf8mb3_general_ci;

alter table appuser
add column doctor_id varchar(15) null after phone,
change column role_code role_code varchar(15) null ,
drop primary key,
add primary key (user_code);


create table exchange_rate (
  ex_code varchar(15) not null,
  comp_code varchar(15) not null,
  ex_date timestamp null default null,
  home_factor double default null,
  home_cur varchar(15) default null,
  target_factor double default null,
  target_cur varchar(15) default null,
  created_date timestamp null default null,
  created_by varchar(15) default null,
  updated_date timestamp null default null,
  updated_by varchar(15) default null,
  deleted bit(1) not null default b'0',
  primary key (ex_code,comp_code)
) engine=innodb default charset=utf8mb3 collate=utf8mb3_general_ci;

drop view if exists v_role_company;
create  view v_role_company as select p.role_code as role_code,p.comp_code as comp_code,p.allow as allow,com.name as name,com.phone as phone,com.address as address,com.start_date as start_date,com.end_date as end_date,com.currency as currency,com.batch_lock as batch_lock,com.year_end_date as year_end_date,com.active as active from (privilege_company p join company_info com on(p.comp_code = com.comp_code));

alter table appuser
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table business_type
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table company_info
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table currency
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();


alter table department
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table mac_prop
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table menu
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table privilege_company
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table privilege_menu
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table project
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table role
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table role_prop
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table seq_table
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table sys_prop
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table exchange_rate
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table mac_prop
add column updated_date timestamp not null default current_timestamp() ON UPDATE current_timestamp();

alter table machine_info
add column mac_address varchar(255) null after pro_update;

alter table machine_info
add column serial_no varchar(255) not null after mac_id;
