CREATE TABLE `book_author` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `book_id` int(11) NOT NULL,
                               `author_id` int(11) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17643 DEFAULT CHARSET=utf8mb4;