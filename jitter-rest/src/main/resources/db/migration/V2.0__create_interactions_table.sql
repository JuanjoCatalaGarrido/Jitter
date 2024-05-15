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
) ENGINE = InnoDB;


-- Insertar muchos datos en la tabla interactions
INSERT INTO `Jitter`.`interactions` (`user_id`, `post_id`, `interaction_type`)
VALUES
-- Interacciones para el primer post
(1, 1, 0),
(2, 1, 1),
(3, 1, 2),
(4, 1, 3),
(5, 1, 4),
(6, 1, 5),
-- Interacciones para el segundo post
(1, 2, 0),
(2, 2, 1),
(3, 2, 2),
(4, 2, 3),
(5, 2, 4),
(6, 2, 5),
-- Interacciones para el tercer post
(1, 3, 0),
(2, 3, 1),
(3, 3, 2),
(4, 3, 3),
(5, 3, 4),
(6, 3, 5),
-- Interacciones para mensajes de Jane Smith
(2, 4, 0),
(3, 4, 1),
(4, 4, 2),
(5, 4, 3),
(6, 4, 4),
(7, 4, 5),
(2, 5, 0),
(3, 5, 1),
(4, 5, 2),
(5, 5, 3),
(6, 5, 4),
(7, 5, 5),
(2, 6, 0),
(3, 6, 1),
(4, 6, 2),
(5, 6, 3),
(6, 6, 4),
(7, 6, 5),
-- Interacciones para mensajes de Alex Jones
(3, 7, 0),
(4, 7, 1),
(5, 7, 2),
(6, 7, 3),
(7, 7, 4),
(8, 7, 5),
(3, 8, 0),
(4, 8, 1),
(5, 8, 2),
(6, 8, 3),
(7, 8, 4),
(8, 8, 5),
(3, 9, 0),
(4, 9, 1),
(5, 9, 2),
(6, 9, 3),
(7, 9, 4),
(8, 9, 5),
-- Interacciones para otros mensajes aleatorios
(4, 10, 0),
(5, 10, 1),
(6, 10, 2),
(7, 10, 3),
(8, 10, 4),
(9, 10, 5),
(4, 11, 0),
(5, 11, 1),
(6, 11, 2),
(7, 11, 3),
(8, 11, 4),
(9, 11, 5),
(4, 12, 0),
(5, 12, 1),
(6, 12, 2),
(7, 12, 3),
(8, 12, 4),
(9, 12, 5),
(5, 13, 0),
(6, 13, 1),
(7, 13, 2),
(8, 13, 3),
(9, 13, 4),
(10, 13, 5),
(5, 14, 0),
(6, 14, 1),
(7, 14, 2),
(8, 14, 3),
(9, 14, 4),
(10, 14, 5),
(5, 15, 0),
(6, 15, 1),
(7, 15, 2),
(8, 15, 3),
(9, 15, 4),
(10, 15, 5);



