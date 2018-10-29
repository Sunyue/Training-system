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
  `chainid` int(11) NOT NULL auto_increment,
  `chainname` varchar(40) DEFAULT NULL UNIQUE,
  `isdefined` int(11) DEFAULT NULL,
  PRIMARY KEY (`chainid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `username` varchar(30) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userrole` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `materialinfo`;
CREATE TABLE `materialinfo` (
  `materialid`   int(11) NOT NULL auto_increment,
  `materialname` varchar(100) NOT NULL,
  `courseid`     int(11) NOT NULL,
  `materialtype` int(11)  DEFAULT 3,
  `attachfilepath` varchar(300) DEFAULT NULL,
  `filetype`       varchar(10) DEFAULT NULL,
  `materialstatus` int(11) DEFAULT 1,
  `materialscore`  float(5,2) DEFAULT 0,
  `materialweight` float(5,2) DEFAULT 0,
  PRIMARY KEY (`materialid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `userchain`;
CREATE TABLE `userchain` (
  `username`     varchar(30) NOT NULL,
  `chainid`      int(11) NOT NULL,
  `status`       int(11) DEFAULT 0,
  PRIMARY KEY (`username`,`chainid`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
