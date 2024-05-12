DROP TABLE IF EXISTS `Jitter`.`tags`;

CREATE TABLE IF NOT EXISTS `Jitter`.`tags`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tag_name`   VARCHAR(20)     NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `tag_id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `tag_name_UNIQUE` (`tag_name` ASC) VISIBLE
) ENGINE = InnoDB;