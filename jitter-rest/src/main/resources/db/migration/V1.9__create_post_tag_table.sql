DROP TABLE IF EXISTS `Jitter`.`post_tag`;

CREATE TABLE IF NOT EXISTS `Jitter`.`post_tag`
(
    `post_id` BIGINT UNSIGNED NOT NULL,
    `tag_id`  BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (`post_id`, `tag_id`),
    INDEX `fk_posts_has_Tag_Tag1_idx` (`tag_id` ASC) VISIBLE,
    INDEX `fk_posts_has_Tag_posts1_idx` (`post_id` ASC) VISIBLE,
    CONSTRAINT `fk_posts_has_Tag_posts1`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_posts_has_Tag_Tag1`
        FOREIGN KEY (`tag_id`)
            REFERENCES `Jitter`.`tags` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- Insertar muchas entradas en la tabla post_tag con diferentes tags para cada post
INSERT INTO `Jitter`.`post_tag` (`post_id`, `tag_id`)
VALUES
-- Entradas para el primer post
(1, 1),
(1, 2),
-- Entradas para el segundo post
(2, 2),
(2, 25),
-- Entradas para el tercer post
(3, 3),
(3, 25),
-- Entradas para los mensajes de Jane Smith
(4, 5),
(4, 6),
(4, 7),
-- Entradas para los mensajes de Alex Jones
(7, 9),
(7, 10),
(7, 11),
-- Entradas para otros mensajes aleatorios
(8, 13),
(8, 14),
(8, 15),
(9, 16),
(9, 17),
(9, 18),
(10, 19),
(10, 20),
(10, 21),
(11, 22),
(11, 23),
(11, 24),
(12, 26),
(12, 27),
(12, 28),
(13, 29),
(13, 30),
(13, 31),
(14, 32),
(14, 33),
(14, 34),
(15, 35),
(15, 36)