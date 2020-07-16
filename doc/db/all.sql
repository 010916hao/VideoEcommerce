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

# insert into `chapter` (`id`, `course_id`, `name`) values('00000001', '00000000', 'testchapter01');
-- 小节
drop table if exists `section`;
create table `section` (
           `id` char(8) not null default '' comment 'id',
           `title` varchar(50) not null comment 'Title',
           `course_id` char(8) comment 'Course|course.id',
           `chapter_id` char(8) comment 'Chapter|chapter.id',
           `video` varchar(200) comment 'Video',
           `time` int comment 'time|unit second',
           `charge` char(1) comment 'charge|C charge；F free',
           `sort` int comment 'sort',
           `created_at` datetime(3) comment 'create time',
           `updated_at` datetime(3) comment 'modify time',
           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='section';

# alter table `section` add column (`vod` char(32) comment 'vod|google vod');

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('00000001', 'test section 01', '00000001', '00000000', '', 500, 'f', 1, now(), now());
