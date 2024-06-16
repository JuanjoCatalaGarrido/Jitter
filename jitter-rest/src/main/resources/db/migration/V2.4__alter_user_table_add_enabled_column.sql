ALTER TABLE `Jitter`.`users`
    ADD COLUMN enabled TINYINT(1) NOT NULL DEFAULT 0;

UPDATE `Jitter`.`users`
SET `enabled` = 1
WHERE `username` IN ('admin', 'jane_smith');

UPDATE `Jitter`.`users`
SET `profile_img_url` = NULL
WHERE `username` IN ('noah_king', 'emma_lee');
