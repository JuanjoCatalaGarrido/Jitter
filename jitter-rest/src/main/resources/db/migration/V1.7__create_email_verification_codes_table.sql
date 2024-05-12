DROP TABLE IF EXISTS `Jitter`.`email_verification_codes`;

CREATE TABLE IF NOT EXISTS `Jitter`.`email_verification_codes`
(
    `id`         BIGINT UNSIGNED      NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED      NOT NULL,
    `code`       SMALLINT UNSIGNED NOT NULL,
    `created_at` DATETIME          NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `notification_id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_notifications_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_notifications_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;

