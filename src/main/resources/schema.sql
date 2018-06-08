

CREATE TABLE IF NOT EXISTS movie (
  id VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  umid VARCHAR(255) NOT NULL,
  release_year VARCHAR(255) NOT NULL,
  runtime VARCHAR(255) NOT NULL,
  rating VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- INSERT INTO movie(id, title, umid, release_year, runtime, rating) VALUES ('abc123', 'Snow White', 'ABC-DEF', '1935', '2 hours','PG-13');
-- INSERT INTO movie(id, title, umid, release_year, runtime, rating) VALUES ('abc456', 'Cinderella', 'ABC-DEF', '1935', '2 hours','PG-13');
-- INSERT INTO movie(id, title, umid, release_year, runtime, rating) VALUES ('abc890', 'Sleeping Beauty', 'ABC-DEF', '1935', '2 hours','PG-13');