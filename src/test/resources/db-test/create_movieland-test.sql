CREATE TABLE user_account
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  email                                VARCHAR       NOT NULL,
  first_name                           VARCHAR       NOT NULL,
  last_name                            VARCHAR       NOT NULL,
  password_hash                        VARCHAR       NOT NULL,
  salt                                 VARCHAR       NOT NULL,
  iterations                           INT           NOT NULL
)
;
CREATE UNIQUE INDEX urat_id_uindex     ON user_account (id)
;
CREATE UNIQUE INDEX urat_email_uindex  ON user_account (email)
;


CREATE TABLE user_role
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  code                                 VARCHAR       NOT NULL,
  title                                VARCHAR       NOT NULL
)
;
CREATE UNIQUE INDEX urre_id_uindex     ON user_role (id)
;
CREATE UNIQUE INDEX urre_code_uindex   ON user_role (code)
;
CREATE UNIQUE INDEX urre_title_uindex  ON user_role (title)
;


CREATE TABLE ref_user_account_role
( user_id                              INT           NOT NULL,
  user_role_id                         INT           NOT NULL
)
;
ALTER TABLE ref_user_account_role
 ADD CONSTRAINT ruat_pk                PRIMARY KEY (user_id, user_role_id)
;
CREATE UNIQUE INDEX ruat_pk_uindex     ON ref_user_account_role (user_id, user_role_id)
;
ALTER TABLE ref_user_account_role
 ADD CONSTRAINT ruat_urat_fk           FOREIGN KEY (user_id) 
     REFERENCES user_account (id)
;
ALTER TABLE ref_user_account_role
 ADD CONSTRAINT ruat_urre_fk           FOREIGN KEY (user_role_id) 
     REFERENCES user_role    (id)
;

CREATE TABLE d_genre
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  title                                VARCHAR       NOT NULL
)
;
CREATE UNIQUE INDEX dgre_id_uindex     ON d_genre (id)
;
CREATE UNIQUE INDEX dgre_title_uindex  ON d_genre (title)
;


CREATE TABLE d_country
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  title                                VARCHAR       NOT NULL
);
CREATE UNIQUE INDEX dcry_id_uindex     ON d_country (id)
;
CREATE UNIQUE INDEX dcry_title_uindex  ON d_country (title)
;


CREATE TABLE movie
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  title                                VARCHAR       NOT NULL,
  title_original                       VARCHAR       NOT NULL,
  release_year                         INT           NOT NULL,
  description                          VARCHAR,
  rating                               NUMERIC(3,1),
  price                                NUMERIC(9,2)  NOT NULL
)
;
CREATE UNIQUE INDEX movie_id_uindex    ON movie (id)
;


CREATE TABLE review
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  movie_id                             INT           NOT NULL,
  user_id                              INT           NOT NULL,
  review_text                          VARCHAR       NOT NULL
)
;
CREATE UNIQUE INDEX reew_id_uindex     ON review (id)
;
ALTER TABLE review
 ADD CONSTRAINT reew_movie_fk          FOREIGN KEY (movie_id)
     REFERENCES movie        (id)
;
ALTER TABLE review
 ADD CONSTRAINT reew_urat_fk           FOREIGN KEY (user_id)
     REFERENCES user_account (id)
;


CREATE TABLE movie_poster
( id                                   SERIAL        NOT NULL PRIMARY KEY,
  movie_id                             INT           NOT NULL,
  poster_url                           VARCHAR       NOT NULL
)
;
CREATE UNIQUE INDEX mepr_id_uindex     ON movie_poster (id)
;
ALTER TABLE movie_poster
 ADD CONSTRAINT mepr_movie_fk          FOREIGN KEY (movie_id)
     REFERENCES movie        (id)
;


CREATE TABLE ref_movie_genre
( movie_id                             INT           NOT NULL,
  genre_id                             INT           NOT NULL
)
;
ALTER TABLE ref_movie_genre
 ADD CONSTRAINT rmre_pk                PRIMARY KEY (movie_id, genre_id)
;
CREATE UNIQUE INDEX rmre_pk_uindex     ON ref_movie_genre (movie_id, genre_id)
;
ALTER TABLE ref_movie_genre
 ADD CONSTRAINT rmre_movie_fk           FOREIGN KEY (movie_id) 
     REFERENCES movie        (id)
;
ALTER TABLE ref_movie_genre
 ADD CONSTRAINT rmre_genre_fk           FOREIGN KEY (genre_id) 
     REFERENCES d_genre      (id)
;


CREATE TABLE ref_movie_country
( movie_id                             INT           NOT NULL,
  country_id                           INT           NOT NULL
)
;
ALTER TABLE ref_movie_country
 ADD CONSTRAINT rmcy_pk                PRIMARY KEY (movie_id, country_id)
;
CREATE UNIQUE INDEX rmcy_pk_uindex     ON ref_movie_country (movie_id, country_id)
;
ALTER TABLE ref_movie_country
 ADD CONSTRAINT rmcy_movie_fk           FOREIGN KEY (movie_id) 
     REFERENCES movie        (id)
;
ALTER TABLE ref_movie_country
 ADD CONSTRAINT rmccy_genre_fk           FOREIGN KEY (country_id) 
     REFERENCES d_country      (id)
;

CREATE OR REPLACE VIEW v_movie_three_random AS
  SELECT moie.id
       , moie.title
       , moie.title_original
       , moie.release_year
       , moie.rating
       , moie.price
       , mepr.poster_url
   FROM movie               moie
        JOIN
           movie_poster        mepr
           ON mepr.movie_id  = moie.id
  ORDER BY RANDOM()
;
