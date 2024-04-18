set FOREIGN_KEY_CHECKS = 0;
truncate table `users`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users` (`id`, `name`, `balance`)
VALUES(1, 'Alice', 100);
