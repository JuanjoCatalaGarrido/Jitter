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

INSERT INTO `Jitter`.`email_verification_codes` (`user_id`, `code`)
VALUES
    (1, 1234),
    (2, 5678),
    (3, 9012),
    (4, 3456),
    (5, 7890),
    (6, 2345),
    (7, 6789),
    (8, 1234),
    (9, 5678),
    (10, 9012),
    (11, 3456),
    (12, 7890),
    (13, 2345),
    (14, 6789),
    (15, 1234),
    (1, 5678),
    (1, 9012),
    (2, 1234),
    (3, 5678),
    (4, 9012);
