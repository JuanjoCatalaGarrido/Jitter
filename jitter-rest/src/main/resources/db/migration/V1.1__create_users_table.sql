DROP TABLE IF EXISTS `Jitter`.`users`;

CREATE TABLE IF NOT EXISTS `Jitter`.`users`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username`        VARCHAR(45)     NOT NULL,
    `password`        VARCHAR(100)    NOT NULL,
    `email`           VARCHAR(45)     NOT NULL,
    `profile_img_url` VARCHAR(100)    NULL,
    `verified_at`     TIMESTAMP       NULL,
    `created_at`      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    UNIQUE INDEX `email_address_UNIQUE` (`email` ASC) VISIBLE
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`users` (`username`, `password`, `email`, `profile_img_url`)
VALUES ('john_doe', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'john.doe@example.com', '/static/profile-img/john_doe.jpg'),
       ('jane_smith', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'jane.smith@example.com', '/static/profile-img/jane_smith.jpg'),
       ('alex_jones', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'alex.jones@example.com', '/static/profile-img/alex_jones.jpg'),
       ('emily_williams', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'emily.williams@example.com', '/static/profile-img//emily_williams.jpg'),
       ('michael_brown', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'michael.brown@example.com', '/static/profile-img//michael_brown.jpg'),
       ('sophia_adams', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'sophia.adams@example.com', '/static/profile-img/sophia_adams.jpg'),
       ('william_clark', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'william.clark@example.com', '/static/profile-img/william_clark.jpg'),
       ('olivia_hill', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'olivia.hill@example.com', '/static/profile-img/olivia_hill.jpg'),
       ('noah_king', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'noah.king@example.com', '/static/profile-img/noah_king.jpg'),
       ('emma_lee', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'emma.lee@example.com', '/static/profile-img/emma_lee.jpg'),
       ('liam_martin', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'liam.martin@example.com', '/static/profile-img/liam_martin.jpg'),
       ('ava_garcia', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'ava.garcia@example.com', '/static/profile-img/ava_garcia.jpg'),
       ('james_rodriguez', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'james.rodriguez@example.com', '/static/profile-img/james_rodriguez.jpg'),
       ('olivia_martinez', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'olivia.martinez@example.com', '/static/profile-img/olivia_martinez.jpg'),
       ('noah_anderson', '$2y$10$qk0.2SZ4t7F.bVVfGT24seS2.s8oVL3E5e4cqyI2CYK.Vm9UuYJWS',
        'noah.anderson@example.com', '/static/profile-img/noah_anderson.jpg');

