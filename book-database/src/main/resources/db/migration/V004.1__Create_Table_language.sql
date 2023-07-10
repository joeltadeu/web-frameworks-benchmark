CREATE TABLE `language`
(
    `id`   int(11)    NOT NULL AUTO_INCREMENT,
    `code` varchar(8) NOT NULL,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;