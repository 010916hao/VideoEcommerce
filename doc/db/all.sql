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

insert into `chapter` (`id`, `course_id`, `name`) values('00000001', '00000000', 'testchapter01');
insert into `chapter` (`id`, `course_id`, `name`) values('00000002', '00000000', 'testchapter02');
insert into `chapter` (`id`, `course_id`, `name`) values('00000003', '00000000', 'testchapter03');
insert into `chapter` (`id`, `course_id`, `name`) values('00000004', '00000000', 'testchapter04');
insert into `chapter` (`id`, `course_id`, `name`) values('00000005', '00000000', 'testchapter05');
insert into `chapter` (`id`, `course_id`, `name`) values('00000006', '00000000', 'testchapter06');
insert into `chapter` (`id`, `course_id`, `name`) values('00000007', '00000000', 'testchapter07');
insert into `chapter` (`id`, `course_id`, `name`) values('00000008', '00000000', 'testchapter08');
insert into `chapter` (`id`, `course_id`, `name`) values('00000009', '00000000', 'testchapter09');
insert into `chapter` (`id`, `course_id`, `name`) values('00000010', '00000000', 'testchapter10');
insert into `chapter` (`id`, `course_id`, `name`) values('00000011', '00000000', 'testchapter11');
insert into `chapter` (`id`, `course_id`, `name`) values('00000012', '00000000', 'testchapter12');
insert into `chapter` (`id`, `course_id`, `name`) values('00000013', '00000000', 'testchapter13');
insert into `chapter` (`id`, `course_id`, `name`) values('00000014', '00000000', 'testchapter14');
insert into `chapter` (`id`, `course_id`, `name`) values('00000015', '00000000', 'testchapter15');
insert into `chapter` (`id`, `course_id`, `name`) values('00000016', '00000000', 'testchapter16');
insert into `chapter` (`id`, `course_id`, `name`) values('00000017', '00000000', 'testchapter17');
insert into `chapter` (`id`, `course_id`, `name`) values('00000018', '00000000', 'testchapter18');
insert into `chapter` (`id`, `course_id`, `name`) values('00000019', '00000000', 'testchapter19');
insert into `chapter` (`id`, `course_id`, `name`) values('00000020', '00000000', 'testchapter20');
