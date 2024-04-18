set FOREIGN_KEY_CHECKS = 0;
truncate table `seats`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `seats` (`id`, `amount`, `number`, `status`, `show_id`, `hold_time`)
VALUES (1, 10000, 1, 'EMPTY', 1, '2025-04-15 13:06:11')
