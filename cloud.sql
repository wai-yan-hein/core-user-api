alter table `department` 
add column `acc_queue` varchar(255) null after `inv_queue`,
change column `queue_name` `inv_queue` varchar(255) null default null ;

set sql_safe_updates=0;
update menu
set menu_class ='Inventory'
where menu_class='Report'

ALTER TABLE `menu`
ADD COLUMN `comp_code` VARCHAR(15) NOT NULL AFTER `menu_code`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`menu_code`, `comp_code`),
DROP INDEX `menu_id` ;

ALTER TABLE `role_prop`
ADD COLUMN `comp_code` VARCHAR(15) NOT NULL AFTER `prop_key`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`role_code`, `prop_key`, `comp_code`);

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

drop view if exists v_role_company;
create  view v_role_company as select p.role_code as role_code,p.comp_code as comp_code,p.allow as allow,com.name as name,com.phone as phone,com.address as address,com.start_date as start_date,com.end_date as end_date,com.currency as currency from (privilege_company p join company_info com on(p.comp_code = com.comp_code));

drop view if exists v_role_menu;
create  view v_role_menu as select p.menu_code as menu_code,p.role_code as role_code,p.comp_code as comp_code,p.allow as allow,m.menu_name as menu_name,m.menu_url as menu_url,m.menu_type as menu_type,m.menu_class as menu_class,m.account as account,m.parent_menu_code as parent_menu_code,m.order_by as order_by from (privilege_menu p join menu m on(p.menu_code = m.menu_code and p.comp_code = m.comp_code));
