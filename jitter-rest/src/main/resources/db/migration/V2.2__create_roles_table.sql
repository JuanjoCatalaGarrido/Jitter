DROP TABLE IF EXISTS `Jitter`.`roles`;

CREATE TABLE IF NOT EXISTS `Jitter`.`roles`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(20)     NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `role_id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `role_name_UNIQUE` (`name` ASC) VISIBLE
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`roles` (`name`)
VALUES ('USER'),
       ('ADMIN'),
       ('OWNER'),
       ('MODERATOR'),
       ('VISITOR');
