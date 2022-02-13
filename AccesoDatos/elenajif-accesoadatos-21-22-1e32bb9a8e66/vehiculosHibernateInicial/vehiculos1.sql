CREATE DATABASE  IF NOT EXISTS `vehiculos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vehiculos`;

DROP TABLE IF EXISTS `propietarios`;

CREATE TABLE `propietarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `propietarios` WRITE;

UNLOCK TABLES;

DROP TABLE IF EXISTS `coches`;

CREATE TABLE `coches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(30) DEFAULT NULL,
  `marca` varchar(40) DEFAULT NULL,
  `modelo` varchar(40) DEFAULT NULL,
  `fecha_matriculacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_propietario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricula` (`matricula`),
  KEY `id_propietario` (`id_propietario`),
  CONSTRAINT `coches_ibfk_1` FOREIGN KEY (`id_propietario`) REFERENCES `propietarios` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

LOCK TABLES `coches` WRITE;
INSERT INTO `coches` VALUES (1,'507','sdfgkfh','507','2018-01-18 06:15:00',NULL),(3,'1234','Audi','hgfdgh','2017-12-15 01:35:00',NULL),(4,'F40','Audi','F40','2017-12-14 02:30:00',NULL),(5,'Focus','Ford','Focus','2018-01-18 06:15:00',NULL),(6,'Cayenne','Porsche','Cayenne','2018-01-21 02:00:00',NULL),(10,'4QWE54RTY23','Porsche','Cayenne','2018-01-21 02:00:00',NULL),(11,'2345','Ferrari','F40','2018-01-20 01:30:00',NULL);
UNLOCK TABLES;


