DROP TABLE IF EXISTS `Jitter`.`posts`;

CREATE TABLE IF NOT EXISTS `Jitter`.`posts`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT UNSIGNED NOT NULL,
    `body`       VARCHAR(500)    NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE INDEX `message_id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_messages_users_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_messages_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `Jitter`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;


INSERT INTO `Jitter`.`posts` (`user_id`, `body`)
VALUES (1, '¡Buenos días a todos! Espero que tengan un excelente día. #FelizLunes'),
       (1,
        'Acabo de leer un artículo muy interesante sobre inteligencia artificial. ¡El futuro es emocionante! 🤖 #IA'),
       (1, '¡Qué increíble concierto de rock anoche! La energía fue simplemente épica. 🎸🤘'),

-- Mensajes de Jane Smith
       (2,
        '¿Alguien tiene recomendaciones para libros de ciencia ficción? ¡Estoy buscando algo nuevo para leer! 📚👽'),
       (2,
        '¡La práctica de yoga por la mañana es la mejor manera de empezar el día con energía y calma! 🧘‍♀️☀️'),
       (2, '¡Hoy es el día de mi maratón! Deseenme suerte. 🏃‍♀️💪 #CorrerEsVida'),

-- Mensajes de Alex Jones
       (3, '¡El viaje en tren de hoy fue espectacular! Paisajes increíbles y compañía genial. 🚂🏞️'),
       (3,
        'Probé una nueva receta de curry tailandés para la cena y fue un éxito total. 🍛👌 ¡Definitivamente la compartiré!'),
       (3, '¿Alguien más disfruta de la fotografía de paisajes tanto como yo? 📸✨ #AmoLaNaturaleza'),

-- Otros mensajes aleatorios
       (4,
        '¡Emocionado por mi próximo viaje a Japón! ¿Alguna recomendación de lugares que visitar en Tokio? 🗼🇯🇵'),
       (5,
        '¡Qué día tan productivo! Logré terminar todos mis pendientes a tiempo. 💼💻 #TrabajoDuro'),
       (6,
        'Acabo de adoptar un gato callejero. ¡Bienvenido a casa, pequeño amigo! 🐱❤️ #AdoptaNoCompres'),
       (7, '¡La noche de juegos con amigos siempre es divertida! 🎲🍕 #NocheDeJuegos'),
       (8, '¡Hoy es mi aniversario de bodas! 5 años juntos y aún más enamorados. 💑💕 #AmorEterno'),
       (9,
        '¿Alguien más está viendo el último episodio de esa serie de televisión? ¡No puedo esperar para discutirlo! 📺🍿'),
       (10,
        '¡Qué día tan hermoso para salir a caminar y disfrutar del aire fresco! 🚶‍♂️🌞 #VidaActiva'),
       (11, '¡Encontré un nuevo restaurante de sushi y me encantó! 🍣😋 #AmanteDelSushi'),
       (12,
        '¡El mejor momento del día es cuando me siento con una taza de té y un buen libro! 📖☕️ #Relax'),
       (13,
        '¡Feliz cumpleaños a mi hermana! Espero que tengas un día tan increíble como tú. 🎂🎉 #Familia'),
       (14, '¡No hay nada como una tarde de domingo sin hacer nada! ☁️🛋️ #Pereza'),
       (15,
        '¡Emocionado por el nuevo proyecto en el que estoy trabajando! Espero que sea un éxito. 💼💡 #Emprendimiento'),
       (4,
        '¡El primer día de clase siempre es emocionante! ¿Alguien más está nervioso? 📚✏️ #Educación'),
       (5, '¡Probando una nueva rutina de ejercicios esta mañana! 💪🏋️‍♂️ #Fitness'),
       (6, '¡La música siempre mejora mi estado de ánimo! 🎶😊 #Música'),
       (7, '¡Hoy es un buen día para hacer una buena acción! 💕🌍 #Solidaridad'),
       (8,
        '¡La lluvia y una taza de chocolate caliente hacen una combinación perfecta! ☔️🍫 #TiempoDeLluvia'),
       (9, '¡Desperté temprano para ver el amanecer y valió la pena! 🌅❤️ #Naturaleza'),
       (10, '¡Planeando unas vacaciones en la playa! 🏖️☀️ #Vacaciones'),
       (11, '¡El aroma del café por la mañana es irresistible! ☕️😌 #Café'),
       (12,
        '¡Hoy es un buen día para empezar ese proyecto que has estado posponiendo! 💼💡 #Productividad'),
       (13,
        '¡Los domingos son para relajarse y recargar energías para la semana que viene! 😌💤 #Domingo'),
       (14, '¡Qué alegría recibir una carta escrita a mano en el correo! 💌✨ #Cartas'),
       (15, '¡Disfrutando de un picnic en el parque con amigos y buena comida! 🧺🌳 #Picnic');

