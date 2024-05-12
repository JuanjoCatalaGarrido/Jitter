DROP TABLE IF EXISTS `Jitter`.`comments`;

CREATE TABLE IF NOT EXISTS `Jitter`.`comments`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `post_id`    BIGINT UNSIGNED NOT NULL,
    `body`       VARCHAR(200)    NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    INDEX `fk_comments_users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_comments_posts1_idx` (`post_id` ASC) VISIBLE,
    CONSTRAINT `fk_comments_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_comments_posts1`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `unique_user_post_interaction`
        UNIQUE (`user_id`, `post_id`)
) ENGINE = InnoDB;
