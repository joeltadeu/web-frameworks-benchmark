CREATE
DATABASE  IF NOT EXISTS `performance-dashboard`;
USE `performance-dashboard`;

DROP TABLE IF EXISTS `build_time`;
CREATE TABLE `build_time`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `time`    float       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `build_time` WRITE;
INSERT INTO `build_time`
VALUES (1, 'micronaut-reactive', 6.909),
       (2, 'micronaut-jdbc', 7.651),
       (3, 'quarkus-reactive', 7.338),
       (4, 'quarkus-hibernate', 7.561),
       (5, 'quarkus-jdbc', 6.511),
       (6, 'springboot-reactive', 3.19),
       (7, 'springboot-hibernate', 3.148),
       (8, 'springboot-jdbc', 2.943),
       (9, 'dotnet-entityframework', 2.01);
UNLOCK TABLES;

DROP TABLE IF EXISTS `compiled_size`;
CREATE TABLE `compiled_size`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `size`    float       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `compiled_size` WRITE;
INSERT INTO `compiled_size`
VALUES (1, 'micronaut-reactive', 25.065),
       (2, 'micronaut-jdbc', 23.833),
       (3, 'quarkus-reactive', 37.3),
       (4, 'quarkus-hibernate', 41.1),
       (5, 'quarkus-jdbc', 22.5),
       (6, 'springboot-reactive', 36.825),
       (7, 'springboot-hibernate', 43.661),
       (8, 'springboot-jdbc', 25.952),
       (9, 'dotnet-entityframework', 17.2);
UNLOCK TABLES;

DROP TABLE IF EXISTS `container_size`;
CREATE TABLE `container_size`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `size`    float       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `container_size` WRITE;
INSERT INTO `container_size`
VALUES (1, 'micronaut-jdbc', 350),
       (2, 'micronaut-reactive', 351),
       (3, 'quarkus-jdbc', 424),
       (4, 'quarkus-hibernate', 444),
       (5, 'quarkus-reactive', 440),
       (6, 'springboot-reactive', 363),
       (7, 'springboot-jdbc', 352),
       (8, 'springboot-hibernate', 370),
       (9, 'dotnet-entityframework', 118);
UNLOCK TABLES;

DROP TABLE IF EXISTS `executions`;
CREATE TABLE `executions`
(
    `id`             int(11)     NOT NULL AUTO_INCREMENT,
    `project`        varchar(45) NOT NULL,
    `users`          int(11)     NOT NULL,
    `time`           int(11)     NOT NULL,
    `total_ok_count` int(11)     NOT NULL,
    `total_ko_count` int(11)     NOT NULL,
    `mean_ok_count`  float       NOT NULL,
    `mean_ko_count`  float       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

LOCK TABLES `executions` WRITE;
INSERT INTO `executions`
VALUES (1, 'springboot-hibernate', 100, 10, 12532, 0, 18.677, 0),
       (2, 'springboot-hibernate', 500, 10, 57868, 0, 86.241, 0),
       (3, 'springboot-jdbc', 100, 10, 12566, 0, 18.755, 0),
       (4, 'springboot-jdbc', 500, 10, 54760, 0, 81.61, 0),
       (5, 'springboot-reactive', 100, 10, 12464, 0, 18.575, 0),
       (6, 'springboot-reactive', 500, 10, 37632, 0, 55.917, 0),
       (7, 'micronaut-jdbc', 100, 10, 12510, 0, 18.672, 0),
       (8, 'micronaut-jdbc', 500, 10, 50180, 0, 74.784, 0),
       (9, 'micronaut-reactive', 100, 10, 11144, 0, 16.608, 0),
       (10, 'micronaut-reactive', 500, 10, 26153, 5073, 38.745, 7.516),
       (11, 'quarkus-jdbc', 100, 10, 12536, 0, 18.71, 0),
       (12, 'quarkus-jdbc', 500, 10, 47762, 0, 71.18, 0),
       (13, 'quarkus-hibernate', 100, 10, 12528, 0, 18.671, 0),
       (14, 'quarkus-hibernate', 500, 10, 48126, 0, 71.51, 0),
       (15, 'quarkus-reactive', 100, 10, 11736, 0, 17.49, 0),
       (16, 'quarkus-reactive', 500, 10, 19214, 0, 28.132, 0),
       (17, 'dotnet-entityframework', 100, 10, 12554, 0, 18.737, 0),
       (18, 'dotnet-entityframework', 500, 10, 45792, 0, 68.042, 0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `response_time`;
CREATE TABLE `response_time`
(
    `id`                 int(11)     NOT NULL AUTO_INCREMENT,
    `project`            varchar(45) NOT NULL,
    `endpoint`           varchar(45) NOT NULL,
    `users`              int(11)     NOT NULL,
    `time`               int(11)     NOT NULL,
    `min`                int(11)     NOT NULL,
    `50th`               int(11)     NOT NULL,
    `75th`               int(11)     NOT NULL,
    `95th`               int(11)     NOT NULL,
    `99th`               int(11)     NOT NULL,
    `max`                int(11)     NOT NULL,
    `mean`               int(11)     NOT NULL,
    `standard_deviation` int(11)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

LOCK TABLES `response_time` WRITE;
INSERT INTO `response_time`
VALUES (1, 'springboot-hibernate', 'Get all books', 100, 10, 45, 56, 62, 76, 91, 490, 58, 10),
       (2, 'springboot-hibernate', 'Get all books', 500, 10, 45, 331, 528, 1111, 2044, 3635, 424, 367),
       (3, 'springboot-jdbc', 'Get all books', 100, 10, 29, 36, 42, 59, 79, 302, 40, 13),
       (4, 'springboot-jdbc', 'Get all books', 500, 10, 28, 492, 1417, 2209, 2780, 3524, 789, 782),
       (5, 'springboot-reactive', 'Get all books', 100, 10, 61, 77, 86, 111, 135, 868, 81, 16),
       (6, 'springboot-reactive', 'Get all books', 500, 10, 67, 2417, 2561, 2936, 3217, 3927, 2327, 552),
       (7, 'micronaut-jdbc', 'Get all books', 100, 10, 50, 64, 73, 92, 112, 625, 67, 14),
       (8, 'micronaut-jdbc', 'Get all books', 500, 10, 50, 1094, 2074, 3278, 4014, 5796, 1291, 1089),
       (9, 'micronaut-reactive', 'Get all books', 100, 10, 195, 600, 764, 1486, 1991, 311, 681, 357),
       (10, 'micronaut-reactive', 'Get all books', 500, 10, 224, 5812, 7410, 9727, 11494, 16327, 5727, 2462),
       (11, 'quarkus-jdbc', 'Get all books', 100, 10, 40, 48, 53, 70, 84, 545, 51, 10),
       (12, 'quarkus-jdbc', 'Get all books', 500, 10, 41, 1470, 2449, 3983, 4720, 5477, 1607, 1245),
       (13, 'quarkus-hibernate', 'Get all books', 100, 10, 50, 57, 62, 76, 96, 656, 60, 11),
       (14, 'quarkus-hibernate', 'Get all books', 500, 10, 51, 1432, 2479, 3355, 3775, 4793, 1526, 1117),
       (15, 'quarkus-reactive', 'Get all books', 100, 10, 129, 364, 426, 638, 758, 1478, 388, 112),
       (16, 'quarkus-reactive', 'Get all books', 500, 10, 125, 8276, 8706, 9473, 10175, 12287, 7920, 1790),
       (18, 'dotnet-entityframework', 'Get all books', 100, 10, 27, 44, 54, 84, 130, 576, 50, 27),
       (19, 'dotnet-entityframework', 'Get all books', 500, 10, 28, 1863, 2399, 3151, 3699, 4791, 1815, 845);
UNLOCK TABLES;

DROP TABLE IF EXISTS `start_memory_usage`;
CREATE TABLE `start_memory_usage`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `usage`   float       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `start_memory_usage` WRITE;
INSERT INTO `start_memory_usage`
VALUES (1, 'micronaut-reactive', 202.5),
       (2, 'micronaut-jdbc', 165.9),
       (3, 'quarkus-reactive', 227.4),
       (4, 'quarkus-hibernate', 254.3),
       (5, 'quarkus-jdbc', 226.9),
       (6, 'springboot-reactive', 250.6),
       (7, 'springboot-hibernate', 264.3),
       (8, 'springboot-jdbc', 213.8),
       (9, 'dotnet-entityframework', 66);
UNLOCK TABLES;

DROP TABLE IF EXISTS `start_time`;
CREATE TABLE `start_time`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `time`    float DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `start_time` WRITE;
INSERT INTO `start_time`
VALUES (1, 'micronaut-reactive', 977),
       (2, 'micronaut-jbdc', 1338),
       (3, 'quarkus-reactive', 819),
       (4, 'quarkus-hibernate', 1549),
       (5, 'quarkus-jdbc', 744),
       (6, 'springboot-reactive', 2789),
       (7, 'springboot-hibernate', 4736),
       (8, 'springboot-jdbc', 3269),
       (9, 'dotnet-entityframework', 1410);
UNLOCK TABLES;

DROP TABLE IF EXISTS `stressfull_memory_usage`;
CREATE TABLE `stressfull_memory_usage`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `project` varchar(45) NOT NULL,
    `usecase` varchar(45) NOT NULL,
    `usage`   float       NOT NULL,
    `users`   int(11)     NOT NULL,
    `time`    int(11)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

LOCK TABLES `stressfull_memory_usage` WRITE;
INSERT INTO `stressfull_memory_usage`
VALUES (1, 'springboot-jdbc', 'Load Books with 100 users in 10 minutes', 0.69, 100, 10),
       (2, 'springboot-jdbc', 'Load Books with 500 users in 10 min', 3.08, 500, 10),
       (3, 'springboot-hibernate', 'Load Books with 100 users in 10 minutes', 0.978, 100, 10),
       (4, 'springboot-hibernate', 'Load Books with 500 users in 10 minutes', 3.436, 500, 10),
       (5, 'micronaut-reactive', 'Load Books with 100 users in 10 minutes', 1.39, 100, 10),
       (6, 'micronaut-reactive', 'Load Books with 500 users in 10 minutes', 2.186, 500, 10),
       (7, 'springboot-reactive', 'Load Books with 100 users in 10 minutes', 1.414, 100, 10),
       (8, 'springboot-reactive', 'Load Books with 500 users in 10 minutes', 2.686, 500, 10),
       (9, 'micronaut-jdbc', 'Load Books with 100 users in 10 minutes', 1.104, 100, 10),
       (10, 'micronaut-jdbc', 'Load Books with 500 users in 10 minutes', 3.962, 500, 10),
       (11, 'quarkus-jdbc', 'Load Books with 100 users in 10 minutes', 4.585, 100, 10),
       (12, 'quarkus-jdbc', 'Load Books with 500 users in 10 minutes', 4.621, 500, 10),
       (13, 'quarkus-hibernate', 'Load Books with 100 users in 10 minutes', 4.634, 100, 10),
       (14, 'quarkus-hibernate', 'Load Books with 500 users in 10 minutes', 3.767, 500, 10),
       (15, 'quarkus-reactive', 'Load Books with 100 users in 10 minutes', 0.938, 100, 10),
       (16, 'quarkus-reactive', 'Load Books with 500 users in 10 minutes', 0.404, 500, 10),
       (17, 'dotnet-entityframework', 'Load Books with 100 users in 10 minutes', 3.035, 100, 10),
       (18, 'dotnet-entityframework', 'Load Books with 500 users in 10 minutes', 3.365, 500, 10);
UNLOCK TABLES;