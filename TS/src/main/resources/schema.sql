DROP TABLE IF EXISTS `courseinfo`;
CREATE TABLE `courseinfo` (
  `courseid` int(11) NOT NULL,
  `coursename` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `courselayer` int(11) DEFAULT NULL,
  `parentcid` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `chaincourse`;
CREATE TABLE `chaincourse` (
  `chainid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `seqorder` int(11) DEFAULT '0',
  PRIMARY KEY (`chainid`,`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `chaininfo`;
CREATE TABLE `chaininfo` (
  `chainid` int(11) NOT NULL,
  `chainname` varchar(40) DEFAULT NULL,
  `isdefined` int(11) DEFAULT NULL,
  PRIMARY KEY (`chainid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;