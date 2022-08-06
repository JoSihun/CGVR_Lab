create database CGVRLAB;

create user 'admin1'@'localhost' identified by '15161516';
flush privileges;
select user, host, authentication_string from mysql.user;

delete 
from mysql.user
where user = 'admin';

GRANT ALL PRIVILEGES ON cgvrlab.* TO 'admin1'@'localhost';
FLUSH PRIVILEGES;	