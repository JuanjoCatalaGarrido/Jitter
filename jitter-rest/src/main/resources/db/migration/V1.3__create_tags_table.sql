DROP TABLE IF EXISTS `Jitter`.`tags`;

CREATE TABLE IF NOT EXISTS `Jitter`.`tags`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(20)     NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `tag_id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `tag_name_UNIQUE` (`name` ASC) VISIBLE
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`tags` (`name`)
VALUES ('FelizLunes'),
       ('IA'),
       ('Rock'),
       ('Asado'),
       ('CienciaFicción'),
       ('Yoga'),
       ('CorrerEsVida'),
       ('Vegana'),
       ('Tren'),
       ('Recetas'),
       ('Fotografía'),
       ('ProductosLocales'),
       ('ViajeAJapón'),
       ('TrabajoDuro'),
       ('AdoptaNoCompres'),
       ('NocheDeJuegos'),
       ('AmorEterno'),
       ('SeriesTV'),
       ('VidaActiva'),
       ('Sushi'),
       ('Relax'),
       ('Familia'),
       ('Pereza'),
       ('Emprendimiento'),
       ('Educación'),
       ('Fitness'),
       ('Música'),
       ('Solidaridad'),
       ('TiempoDeLluvia'),
       ('Naturaleza'),
       ('Vacaciones'),
       ('Café'),
       ('Productividad'),
       ('Domingo'),
       ('Cartas'),
       ('Picnic');
