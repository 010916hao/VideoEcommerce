-- test
drop table if exists `test`;
CREATE TABLE `test` (
  `id` char(8) DEFAULT '' NOT NULL,
  `name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='test';

insert into `test` (`id`, `name`) values(1, 'yihao');
insert into `test` (`id`, `name`) values(2, 'liu');

-- Chapter
drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'id',
    `course_id` char(8) comment 'course id',
    `name` varchar(50) comment 'course name',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='chapter';

insert into `chapter` (`id`, `course_id`, `name`) values('00000000', '00000001', 'testchapter1');
insert into `chapter` (`id`, `course_id`, `name`) values('00000001', '00000002', 'testchapter2');