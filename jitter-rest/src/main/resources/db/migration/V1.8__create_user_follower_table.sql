DROP TABLE IF EXISTS `Jitter`.`user_follower`;

CREATE TABLE IF NOT EXISTS `Jitter`.`user_follower`
(
    `user_id`     BIGINT UNSIGNED NOT NULL,
    `follower_id` BIGINT UNSIGNED NOT NULL,
    `created_at`  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`user_id`, `follower_id`),
    INDEX `fk_users_has_users_users2_idx` (`follower_id` ASC) VISIBLE,
    INDEX `fk_users_has_users_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_users_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_users_users2`
        FOREIGN KEY (`follower_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`user_follower` (`user_id`, `follower_id`)
VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10),
    (1, 11),
    (1, 12),
    (1, 13),
    (1, 14),
    (1, 15),
    (2, 1),
    (2, 3),
    (2, 4),
    (3, 2),
    (3, 4),
    (3, 5),
    (4, 3),
    (4, 5),
    (4, 6),
    (5, 4),
    (5, 6),
    (5, 7),
    (6, 5),
    (6, 7),
    (6, 8),
    (7, 6),
    (7, 8),
    (7, 9),
    (8, 7),
    (8, 9),
    (8, 10),
    (9, 8),
    (9, 10),
    (9, 11),
    (10, 9),
    (10, 11),
    (10, 12),
    (11, 10),
    (11, 12),
    (11, 13),
    (12, 11),
    (12, 13),
    (12, 14),
    (13, 12),
    (13, 14),
    (13, 15),
    (14, 13),
    (14, 15),
    (14, 1),
    (15, 14),
    (15, 1),
    (15, 2);
