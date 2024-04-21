set FOREIGN_KEY_CHECKS = 0;
truncate table `wait_tokens`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `wait_tokens` (`id`, `expired_at`, `status`, `token`, `user_id`)
VALUES(1, '2025-04-15 13:06:11', 'ONGOING', 'token', 1);
