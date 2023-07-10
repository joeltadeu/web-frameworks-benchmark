CREATE TABLE `book` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `title` varchar(400) NOT NULL,
                        `isbn` varchar(13) NOT NULL,
                        `language_id` int(11) NOT NULL,
                        `num_pages` int(11) NOT NULL,
                        `publication_date` date NOT NULL,
                        `publisher_id` int(11) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11128 DEFAULT CHARSET=utf8mb4;