alter table `department` 
add column `acc_queue` varchar(255) null after `inv_queue`,
change column `queue_name` `inv_queue` varchar(255) null default null ;
