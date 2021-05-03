
CREATE SEQUENCE seq_usuario;
CREATE TABLE usuario (
 id integer CONSTRAINT pk_usuario PRIMARY key ,
 nome varchar(100)  NOT NULL,
 email varchar(100)  NOT NULL,
 senha varchar(100)  NOT NULL,
 data_criacao timestamp NOT NULL
);

CREATE SEQUENCE seq_post;
CREATE TABLE post (
 id integer CONSTRAINT pk_post PRIMARY KEY,
 id_usuario integer NOT NULL,
 texto text NOT NULL,
 data_criacao timestamp NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE SEQUENCE seq_album;
CREATE TABLE album (
 id integer CONSTRAINT pk_album PRIMARY KEY,
 id_usuario integer NOT NULL,
 titulo varchar(200) NOT NULL,
 data_criacao timestamp NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE SEQUENCE seq_foto;
CREATE TABLE foto (
 id integer CONSTRAINT pk_foto PRIMARY KEY,
 id_post integer NULL,
 id_album integer NULL,
 caminho varchar(200) NOT NULL,
 data_criacao timestamp NOT NULL,
  FOREIGN KEY (id_post) REFERENCES post (id) ON DELETE cascade,
  FOREIGN KEY (id_album) REFERENCES album (id) ON DELETE CASCADE
);

CREATE SEQUENCE seq_comentario;
CREATE TABLE comentario (
 id integer CONSTRAINT pk_comentario PRIMARY KEY,
 id_usuario integer NOT NULL,
 id_post integer NOT NULL,
 data_criacao timestamp NOT NULL,
 texto text,
 FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE CASCADE,
 FOREIGN KEY (id_post) REFERENCES post (id) ON DELETE CASCADE
);