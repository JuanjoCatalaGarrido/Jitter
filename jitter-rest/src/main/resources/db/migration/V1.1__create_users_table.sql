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
VALUES ('admin', '$2a$12$BAbVvYLTUSKKJxsx9MLAPezBy/3xL6iHBOdHR3pCzcgdgqVs3waz6',
        'admin@admin.com', '/static/profile-img/admin.jpg'),
       ('jane_smith', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'jane.smith@example.com', '/static/profile-img/jane_smith.jpg'),
       ('alex_jones', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'alex.jones@example.com', '/static/profile-img/alex_jones.jpg'),
       ('emily_williams', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'emily.williams@example.com', '/static/profile-img//emily_williams.jpg'),
       ('michael_brown', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'michael.brown@example.com', '/static/profile-img//michael_brown.jpg'),
       ('sophia_adams', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'sophia.adams@example.com', '/static/profile-img/sophia_adams.jpg'),
       ('william_clark', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'william.clark@example.com', '/static/profile-img/william_clark.jpg'),
       ('olivia_hill', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'olivia.hill@example.com', '/static/profile-img/olivia_hill.jpg'),
       ('noah_king', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'noah.king@example.com', '/static/profile-img/noah_king.jpg'),
       ('emma_lee', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'emma.lee@example.com', '/static/profile-img/emma_lee.jpg'),
       ('liam_martin', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'liam.martin@example.com', '/static/profile-img/liam_martin.jpg'),
       ('ava_garcia', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'ava.garcia@example.com', '/static/profile-img/ava_garcia.jpg'),
       ('james_rodriguez', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'james.rodriguez@example.com', '/static/profile-img/james_rodriguez.jpg'),
       ('olivia_martinez', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'olivia.martinez@example.com', '/static/profile-img/olivia_martinez.jpg'),
       ('noah_anderson', '$2a$12$A3QNMEsKtZhn.njPGVE91.5y47.J8orFEWnRDzU5lSeAjt1AOWLZ.',
        'noah.anderson@example.com', '/static/profile-img/noah_anderson.jpg');

