CREATE TABLE IF NOT EXISTS `Users` (
    `user_id`   VARCHAR(50) NOT NULL,
    `password`  VARCHAR(50) NOT NULL,
    `age`       INT         NOT NULL

    PRIMARY KEY(`user_id`)
);

MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'admin', '12345', 25 );
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'dongmyo', '67890', 35 );
