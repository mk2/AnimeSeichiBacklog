DROP TABLE IF EXISTS asb_tag;

CREATE TABLE asb_tag
(
  tag_id      SERIAL    NOT NULL PRIMARY KEY,
  tag         VARCHAR   NOT NULL,
  create_date TIMESTAMP NOT NULL DEFAULT (now())
);
