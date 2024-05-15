DROP TABLE IF EXISTS `Jitter`.`user_preferences`;

CREATE TABLE IF NOT EXISTS `Jitter`.`user_preferences`
(
    `user_id`   BIGINT UNSIGNED  NOT NULL,
    `dark_mode` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    `telemetry` TINYINT UNSIGNED NOT NULL DEFAULT 0,

    PRIMARY KEY (`user_id`),
    INDEX `fk_preferences_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_preferences_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`user_preferences` (`user_id`, `dark_mode`, `telemetry`)
VALUES (1, 1, 0),
       (2, 0, 1),
       (3, 1, 1),
       (4, 0, 0),
       (5, 1, 1),
       (6, 0, 0),
       (7, 1, 1),
       (8, 0, 0),
       (9, 1, 0),
       (10, 0, 1),
       (11, 1, 1),
       (12, 0, 0),
       (13, 1, 0),
       (14, 0, 1),
       (15, 1, 0);
