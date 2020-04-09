
CREATE TABLE `user` (
  `id` INT NOT NULL auto_increment,
  `username` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  `birthday` DATETIME NULL,

  PRIMARY KEY (`id`));
  
CREATE TABLE `address` (
  `userid` INT NOT NULL,
  `address` VARCHAR(100) NOT NULL
);

  
  
insert into user(id, username, age, birthday) values(1, "jzz", 20, str_to_date('1993-01-01', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(2, "jzl", 18, str_to_date('1993-01-02', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(3, "jxy", 17, str_to_date('1993-01-03', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(4, "jzz", 20, str_to_date('1993-01-02', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(5, "jzl", 18, str_to_date('1993-01-03', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(6, "jxy", 17, str_to_date('2003-01-04', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(7, "jzz", 20, str_to_date('1993-01-05', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(8, "jzl", 18, str_to_date('1993-01-06', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(9, "jxy", 27, str_to_date('1993-01-07', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(10, "jzz", 20, str_to_date('1983-01-01', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(11, "jzl", 28, str_to_date('1993-01-02', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(12, "jxy", 17, str_to_date('1993-01-03', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(13, "jzl", 18, str_to_date('1993-02-02', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(14, "jxy", 17, str_to_date('1993-03-03', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(15, "jzl", 18, str_to_date('1993-04-02', '%Y-%m-%d'));
insert into user(id, username, age, birthday) values(16, "jxy", 37, str_to_date('1993-05-03', '%Y-%m-%d'));

insert into address(userid, address) values(1, "湖南省常德市");
insert into address(userid, address) values(1, "湖南省长沙市");
insert into address(userid, address) values(2, '湖南省长沙市');
insert into address(userid, address) values(3, '湖南省岳阳市');
insert into address(userid, address) values(3, '福建省泉州市');
insert into address(userid, address) values(3, '福建省厦门市');
insert into address(userid, address) values(4, "湖南省湘潭市");
insert into address(userid, address) values(5, '湖南省株洲市');
insert into address(userid, address) values(6, '湖南省衡阳市');


