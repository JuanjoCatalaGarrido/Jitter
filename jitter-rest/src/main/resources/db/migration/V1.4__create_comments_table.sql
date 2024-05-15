DROP TABLE IF EXISTS `Jitter`.`comments`;

CREATE TABLE IF NOT EXISTS `Jitter`.`comments`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `post_id`    BIGINT UNSIGNED NOT NULL,
    `body`       VARCHAR(200)    NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    INDEX `fk_comments_users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_comments_posts1_idx` (`post_id` ASC) VISIBLE,
    CONSTRAINT `fk_comments_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_comments_posts1`
        FOREIGN KEY (`post_id`)
            REFERENCES `Jitter`.`posts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `unique_user_post_interaction`
        UNIQUE (`user_id`, `post_id`)
) ENGINE = InnoDB;



INSERT INTO `Jitter`.`comments` (`user_id`, `post_id`, `body`)
VALUES
-- Comentarios de John Doe
(1, 1, '¡Qué buenos días! Espero que tengas una excelente jornada también.'),
(1, 2, 'Este artículo suena interesante. ¿Puedes compartir el enlace?'),
(1, 3, '¡Esa energía en los conciertos de rock es incomparable!'),

-- Comentarios de Jane Smith
(2, 4, '¡Te recomendaría "El fin de la eternidad" de Isaac Asimov! Es genial.'),
(2, 5, 'El yoga realmente ayuda a mantener la calma en medio del caos.'),
(2, 6, '¡Buena suerte en tu maratón! Estoy segura de que lo harás genial.'),

-- Comentarios de Alex Jones
(3, 7, 'Los viajes en tren tienen un encanto único, ¿verdad?'),
(3, 8, '¡Esa receta suena deliciosa! ¿Puedes compartirla con nosotros?'),
(3, 9,
 '¡Yo también amo la fotografía de paisajes! Es una forma maravillosa de capturar la belleza de la naturaleza.'),

-- Otros comentarios aleatorios
(4, 10, '¡Japón es increíble! No te pierdas el barrio de Harajuku en Tokio.'),
(5, 11, '¡Bien hecho! Siempre es satisfactorio terminar todas las tareas.'),
(6, 12, '¡Qué lindo gesto! Los gatos callejeros merecen todo nuestro amor.'),
(7, 13, '¡Las noches de juegos son la mejor forma de divertirse con amigos!'),
(8, 14, '¡Feliz aniversario! Que sigan los momentos felices juntos.'),
(9, 15, '¡Esa serie es adictiva! ¿Qué te pareció el final?'),
(10, 16, '¡Disfruta de tu caminata! El contacto con la naturaleza siempre revitaliza.'),
(11, 17, '¡Amo el sushi! ¿Cuál fue tu rollo favorito?'),
(12, 18, '¡Esos momentos de tranquilidad son sagrados! ¿Qué libro estás leyendo?'),
(13, 19, '¡Feliz cumpleaños para ella! Que tenga un día lleno de alegría.'),
(14, 20, '¡Los domingos de pereza son necesarios de vez en cuando!'),
(15, 21, '¡Buena suerte con tu proyecto! Estoy seguro de que será un éxito.'),
(4, 22, '¡La primera semana de clases siempre es emocionante! ¿Qué curso estás tomando?'),
(5, 23, '¡Esa rutina te mantendrá en forma! ¿Alguna recomendación para ejercicios abdominales?'),
(6, 24, '¡La música es mi terapia! ¿Qué género es tu favorito?'),
(7, 25, '¡Es genial hacer el bien! ¿Qué hiciste hoy?'),
(8, 26, '¡Esa combinación suena divina! ¿Te gusta la lluvia?'),
(9, 27, '¡El amanecer es mágico! ¿Dónde viste el amanecer hoy?'),
(10, 28, '¡Las vacaciones en la playa son perfectas para relajarse! ¿Cuál es tu destino soñado?'),
(11, 29, '¡El café es mi combustible! ¿Tienes alguna cafetería favorita?'),
(12, 30, '¡Hoy es el día para tomar acción! ¿Qué proyecto empezarás?'),
(13, 31, '¡Los domingos son para mimarse a uno mismo! ¿Tienes alguna rutina especial?'),
(14, 32, '¡Las cartas son un tesoro! ¿Cuándo fue la última vez que recibiste una?'),
(15, 33,
 '¡Los picnics son una forma encantadora de pasar el tiempo al aire libre! ¿Qué llevaste para comer?');

