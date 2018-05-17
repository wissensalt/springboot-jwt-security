CREATE TABLE user (
  id BIGINT(20) NOT NULL AUTO_INCREMENT ,
  code VARCHAR (50) NOT NULL UNIQUE ,
  name VARCHAR (150),
  password VARCHAR (255),
  enabled TINYINT NOT NULL DEFAULT 1,
  hashed TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE role (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  code VARCHAR(50) NOT NULL UNIQUE ,
  name VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE link_user_role (
  user_id BIGINT(20),
  role_id BIGINT(20),
  PRIMARY KEY (user_id, role_id),
  KEY fk_link_user_user_idx (role_id),
  CONSTRAINT fk_link_user_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_link_role_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
)