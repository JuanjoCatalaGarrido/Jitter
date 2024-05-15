DROP TABLE IF EXISTS `Jitter`.`user_post_share`;

CREATE TABLE IF NOT EXISTS `Jitter`.`user_post_share`
(
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `post_id`    BIGINT UNSIGNED NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`, `post_id`),
    INDEX `fk_users_has_posts_posts1_idx` (`post_id` ASC) VISIBLE,
    INDEX `fk_users_has_posts_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_posts_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_posts_posts1`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`user_post_share` (`user_id`, `post_id`)
VALUES
-- John Doe compartió los primeros tres posts
(1, 1),
(1, 2),
(1, 3),
-- Jane Smith compartió algunos mensajes
(2, 4),
(2, 5),
(2, 6),
-- Alex Jones compartió algunos mensajes
(3, 7),
(3, 8),
(3, 9),
-- Otros usuarios comparten mensajes aleatorios
(5, 11),
(6, 12),
(7, 13),
(8, 14),
(9, 15),
(11, 2),
(12, 3),
(13, 4),
(14, 5),
(15, 6),
(1, 7),
(2, 8),
(4, 10)



