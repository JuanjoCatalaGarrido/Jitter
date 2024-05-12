DROP TABLE IF EXISTS `Litter`.`reports`;

CREATE TABLE IF NOT EXISTS `Litter`.`reports`
(
    `id`         INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `user_id`    INT UNSIGNED     NOT NULL,
    `post_id`    INT UNSIGNED     NOT NULL,
    `importance` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    `details`    VARCHAR(400)     NOT NULL,
    `created_at` TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `fk_users_has_posts_posts2_idx` (`post_id` ASC) VISIBLE,
    INDEX `fk_users_has_posts_users2_idx` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `unique_user_post` (`user_id`, `post_id`),
    CONSTRAINT `fk_users_has_posts_users2`
        FOREIGN KEY (`user_id`)
            REFERENCES `Litter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_posts_posts2`
        FOREIGN KEY (`post_id`)
            REFERENCES `Litter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;