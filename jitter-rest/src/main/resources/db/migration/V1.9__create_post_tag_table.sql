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
)
    ENGINE = InnoDB;
