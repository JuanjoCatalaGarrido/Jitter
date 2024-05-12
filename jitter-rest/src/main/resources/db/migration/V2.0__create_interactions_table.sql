DROP TABLE IF EXISTS `Jitter`.`interactions`;

CREATE TABLE IF NOT EXISTS `Jitter`.`interactions`
(
    `users_user_id`    BIGINT UNSIGNED   NOT NULL,
    `posts_post_id`    BIGINT UNSIGNED   NOT NULL,
    `interaction_type` SMALLINT UNSIGNED NOT NULL,
    `created_at`       TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX `fk_users_has_posts_posts3_idx` (`posts_post_id` ASC) VISIBLE,
    INDEX `fk_users_has_posts_users3_idx` (`users_user_id` ASC) VISIBLE,
    PRIMARY KEY (`users_user_id`, `posts_post_id`),
    CONSTRAINT `fk_users_has_posts_users3`
        FOREIGN KEY (`users_user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_posts_posts3`
        FOREIGN KEY (`posts_post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
