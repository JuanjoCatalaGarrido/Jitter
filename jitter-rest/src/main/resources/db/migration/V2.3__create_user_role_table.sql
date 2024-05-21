DROP TABLE IF EXISTS `Jitter`.`user_role`;

CREATE TABLE IF NOT EXISTS `Jitter`.`user_role`
(
    `user_id` BIGINT UNSIGNED NOT NULL,
    `role_id` BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `fk_users_has_Role_Role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_users_has_Role_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_has_Role_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_Role_Role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `Jitter`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- Insertar muchas entradas en la tabla post_tag con diferentes tags para cada post
INSERT INTO `Jitter`.`user_role` (`user_id`, `role_id`)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5);