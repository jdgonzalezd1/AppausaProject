create database appausa_db;
create user 'appausa_app'@'%' identified by 'appausauser2020';
grant all privileges on appausa_db.* to 'appausa_app'@'%';
alter USER 'appausa_app'@'%' IDENTIFIED WITH mysql_native_password BY 'appausauser2020';
use appausa_db;


select version();
set @@global.time_zone = '+00:00';
set @@session.time_zone = '+00:00';
select @@global.time_zone, @@session.time_zone;

