INSERT INTO user (code, name, password) VALUES ('john', 'John Doe', 'admin123');
INSERT INTO user (code, name, password) VALUES ('fulan', 'Fulan', 'user123');

INSERT INTO role (code, name) VALUES ('role01', 'ROLE_ADMIN');
INSERT INTO role (code, name) VALUES ('role02', 'ROLE_USER');

INSERT INTO link_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO link_user_role (user_id, role_id) VALUES (2, 2);