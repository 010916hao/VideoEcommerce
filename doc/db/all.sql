drop table if exists `test`;

CREATE TABLE `test` (
  `id` char(8) DEFAULT '' NOT NULL,
  `name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='test';

insert into `test` (`id`, `name`) values(1, 'yihao');
insert into `test` (`id`, `name`) values(2, 'liu');