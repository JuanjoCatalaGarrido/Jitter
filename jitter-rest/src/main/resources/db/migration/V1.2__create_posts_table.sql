DROP TABLE IF EXISTS `Jitter`.`posts`;

CREATE TABLE IF NOT EXISTS `Jitter`.`posts`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `body`       VARCHAR(500)    NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `message_id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_messages_users_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_messages_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;