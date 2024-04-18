set FOREIGN_KEY_CHECKS = 0;
truncate table `shows`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `shows` (`id`, `capacity`, `show_time`, `concert_id`)
VALUES(1, 50, now(), 1);
