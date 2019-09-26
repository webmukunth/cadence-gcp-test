create user 'cadence'@'%' identified by 'cadence';
create database cadence;
grant all privileges on cadence.* TO 'cadence'@'%';

create user 'cadence_visibility'@'%' identified by 'cadence_visibility';
create database cadence_visibility;
grant all privileges on cadence_visibility.* TO 'cadence_visibility'@'%';
