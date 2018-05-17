CREATE TABLE user (
  id BIGINT(20) NOT NULL AUTO_INCREMENT ,
  code VARCHAR (50) NOT NULL UNIQUE ,
  name VARCHAR (150),
  password VARCHAR (255),
  enabled BOOLEAN DEFAULT TRUE ,
  account_non_expired BOOLEAN DEFAULT TRUE,
  account_non_locked BOOLEAN DEFAULT TRUE,
  credentials_non_expired BOOLEAN DEFAULT TRUE,
  hashed BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id)
);

CREATE TABLE user_profile (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(100),
  email VARCHAR(150),
  user_id BIGINT(20),
  PRIMARY KEY (id),
  KEY fk_user_profile (user_id),
  CONSTRAINT fk_user_profile FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
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