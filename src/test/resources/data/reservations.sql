set FOREIGN_KEY_CHECKS = 0;
truncate table `reservations`;
set FOREIGN_KEY_CHECKS = 1;

INSERT INTO `reservations` (`id`, `status`, `amount`, `seat_id`, `user_id`)
VALUES(1, 'PENDING', 10000, 1, 1);
