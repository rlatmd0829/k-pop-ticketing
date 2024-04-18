set FOREIGN_KEY_CHECKS = 0;
truncate table `concerts`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `concerts` (`id`, `name`)
VALUES(1, 'concert name');
