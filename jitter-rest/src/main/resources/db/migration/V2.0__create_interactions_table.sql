DROP TABLE IF EXISTS `Jitter`.`interactions`;

CREATE TABLE IF NOT EXISTS `Jitter`.`interactions`
(
    `id`               BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    `user_id`          BIGINT UNSIGNED   NOT NULL,
    `post_id`          BIGINT UNSIGNED   NOT NULL,
    `interaction_type` SMALLINT UNSIGNED NOT NULL,
    `created_at`       TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX `fk_users_has_posts_posts3_idx` (`post_id` ASC) VISIBLE,
    INDEX `fk_users_has_posts_users3_idx` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_users_has_posts_users3`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_posts_posts3`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `unique_user_post_interaction`
        UNIQUE (`user_id`, `post_id`)
)
    ENGINE = InnoDB;
