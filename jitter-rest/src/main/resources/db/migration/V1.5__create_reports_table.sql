DROP TABLE IF EXISTS `Jitter`.`reports`;

CREATE TABLE IF NOT EXISTS `Jitter`.`reports`
(
    `id`         BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED  NOT NULL,
    `post_id`    BIGINT UNSIGNED  NOT NULL,
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
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_posts_posts2`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `unique_user_post_interaction`
        UNIQUE (`user_id`, `post_id`)
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`reports` (`user_id`, `post_id`, `importance`, `details`)
VALUES
-- Reportes de John Doe
(1, 1, 2, 'Este mensaje contiene contenido obsceno.'),
(1, 2, 1, 'El contenido de este mensaje podría ser inapropiado.'),

-- Reportes de Jane Smith
(2, 4, 1, 'Este mensaje parece ser spam.'),
(2, 6, 2, 'Contenido sensible en este mensaje.'),

-- Reportes de Alex Jones
(3, 8, 1, 'Este mensaje podría ser spam.'),

-- Otros reportes aleatorios
(4, 10, 2, 'Este mensaje contiene información incorrecta.'),
(5, 11, 1, 'Se necesita más contexto en este mensaje.'),
(8, 14, 1, 'Este mensaje podría ser spam.'),
(12, 18, 2, 'Contenido sensible en este mensaje.'),
(13, 19, 1, 'Este mensaje parece ser spam.'),
(6, 24, 1, 'Este mensaje podría ser spam.'),
(9, 27, 2, 'Contenido sensible en este mensaje.'),
(11, 29, 1, 'Se necesita más contexto en este mensaje.'),
(14, 32, 1, 'Este mensaje parece ser spam.')
