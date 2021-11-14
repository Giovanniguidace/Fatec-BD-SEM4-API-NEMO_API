DROP TABLE IF EXISTS "client" cascade ;

/* Table 'client' */
CREATE TABLE "client" (
client_id varchar(50),
client_secret varchar(100) NOT NULL,
PRIMARY KEY(client_id));

INSERT INTO client (client_id, client_secret) VALUES
('admin','$2a$10$ixeP95H5dWnQ.SVZM1QJeuKXXxWiUdcFE5xVhj.CASWaLHXj8H8E2');
