set FOREIGN_KEY_CHECKS = 0;
truncate table `reservations`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `reservations` (`id`, `status`, `user_id`, `seat_id`, `amount`)
VALUES(1, 'PENDING', 1, 1, 10000);
