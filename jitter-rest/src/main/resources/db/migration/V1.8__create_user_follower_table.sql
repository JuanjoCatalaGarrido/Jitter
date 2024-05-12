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
            REFERENCES `Litter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_users_users2`
        FOREIGN KEY (`follower_id`)
            REFERENCES `Litter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;
