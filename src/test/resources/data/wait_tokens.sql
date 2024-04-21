set FOREIGN_KEY_CHECKS = 0;
truncate table `wait_tokens`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `wait_tokens` (`id`, `token`, `status`, `expired_at`, `user_id`)
VALUES(1, 'token', 'ONGOING', '2025-04-15 13:06:11', 1);
