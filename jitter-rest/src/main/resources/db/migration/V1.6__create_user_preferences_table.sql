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
