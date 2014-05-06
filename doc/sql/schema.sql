-- generate user name stored procedure
CREATE OR REPLACE FUNCTION asb_make_user_name(TEXT, TEXT)
  RETURNS TEXT AS
  $$
  SELECT
    encode(hmac($1, $2, 'sha512'), 'hex') :: TEXT
  $$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION asb_get_user_id(BIGINT)
  RETURNS INT AS
  $$
  DECLARE
    check_user_id ALIAS FOR $1;
    num_rows INT;
  BEGIN

    SELECT
      COUNT(*)
    INTO num_rows
    FROM asb_user
    WHERE user_id = check_user_id;

    IF num_rows = 0
    THEN
      RETURN NULL;
    ELSE
      RETURN check_user_id;
    END IF;
  END;
  $$ LANGUAGE plpgsql;

DROP VIEW IF EXISTS asb_feature_view;
DROP VIEW IF EXISTS asb_remark_view;
DROP TABLE IF EXISTS asb_remark;
DROP TABLE IF EXISTS asb_feature;
DROP TABLE IF EXISTS asb_role;
DROP TABLE IF EXISTS asb_user;

-- user_name used in internal system for authentication, users can not input that freely.
CREATE TABLE asb_user
(
  user_id                      SERIAL       NOT NULL PRIMARY KEY,
  user_name                    VARCHAR(300) NOT NULL UNIQUE,
  user_alias                   VARCHAR(300) NOT NULL DEFAULT ('NO NAME'),
  user_email                   VARCHAR(300) NOT NULL UNIQUE,
  user_password                VARCHAR(300) NOT NULL,
  user_enabled                 BOOLEAN      NOT NULL DEFAULT (TRUE),
  user_tags                    VARCHAR,
  user_image_path              VARCHAR(300),
  user_reputation              INT          NOT NULL DEFAULT (0),
  user_reputation_voters_count INT          NOT NULL DEFAULT (10),
  user_create_date             TIMESTAMP    NOT NULL DEFAULT (now()),
  user_update_date             TIMESTAMP    NOT NULL DEFAULT (now())
);

CREATE TABLE asb_role
(
  role_id   SERIAL      NOT NULL PRIMARY KEY,
  user_id   INT         NOT NULL REFERENCES asb_user (user_id),
  user_role VARCHAR(10) NOT NULL DEFAULT 'ROLE_USER'
);


CREATE TABLE asb_feature
(
  feature_id                      SERIAL       NOT NULL PRIMARY KEY,
  create_user_id                  INT          NOT NULL REFERENCES asb_user (user_id),
  update_user_id                  INT          NOT NULL REFERENCES asb_user (user_id),
  is_editable                     BOOLEAN      NOT NULL DEFAULT (TRUE),
  is_other_user_editable          BOOLEAN      NOT NULL DEFAULT (FALSE),
  feature_tags                    VARCHAR(500),
  feature_name                    VARCHAR(500) NOT NULL,
  feature_description             VARCHAR      NOT NULL,
  feature_style                   VARCHAR,
  feature_image_path              VARCHAR(300),
  feature_reputation              INT          NOT NULL DEFAULT (0),
  feature_reputation_voters_count INT          NOT NULL DEFAULT (10),
  feature_geom                    GEOMETRY (GEOMETRY, 4326) NOT NULL,
  feature_create_date             TIMESTAMP    NOT NULL DEFAULT (now()),
  feature_update_date             TIMESTAMP    NOT NULL DEFAULT (now())
);


CREATE TABLE asb_remark
(
  remark_id                      SERIAL    NOT NULL PRIMARY KEY,
  feature_id                     INT       NOT NULL REFERENCES asb_feature (feature_id) ON DELETE CASCADE,
  create_user_id                 INT REFERENCES asb_user (user_id) ON DELETE CASCADE,
  remark_message                 VARCHAR   NOT NULL,
  remark_image_path              VARCHAR(300),
  remark_reputation              INT       NOT NULL DEFAULT (0),
  remark_reputation_voters_count INT       NOT NULL DEFAULT (10),
  remark_create_date             TIMESTAMP NOT NULL DEFAULT (now())
);


-- Views
CREATE OR REPLACE VIEW asb_user_view AS
  SELECT
    *
  FROM asb_user
  ORDER BY user_id;

CREATE OR REPLACE VIEW asb_feature_view AS
  SELECT
    asb_feature.feature_id                        AS feature_id,
    asb_create_user.user_id                       AS create_user_id,
    asb_create_user.user_alias                    AS create_user_alias,
    asb_create_user.user_image_path               AS create_user_image_path,
    asb_create_user.user_tags                     AS create_user_tags,
    asb_update_user.user_id                       AS update_user_id,
    asb_update_user.user_alias                    AS update_user_alias,
    asb_update_user.user_image_path               AS update_user_image_path,
    asb_update_user.user_tags                     AS update_user_tags,
    asb_feature.is_editable                       AS is_editable,
    asb_feature.is_other_user_editable            AS is_other_user_editable,
    asb_feature.feature_tags                      AS feature_tags,
    asb_feature.feature_name                      AS feature_name,
    asb_feature.feature_description               AS feature_description,
    asb_feature.feature_style                     AS feature_style,
    asb_feature.feature_image_path                AS feature_image_path,
    asb_feature.feature_reputation                AS feature_reputation,
    asb_feature.feature_reputation_voters_count   AS feature_reputation_voters_count,
    asb_feature.feature_create_date               AS feature_create_date,
    asb_feature.feature_update_date               AS feature_update_date,
    asb_feature.feature_geom                      AS feature_geom,
    ST_AsGeoJSON(asb_feature.feature_geom, 15, 4) AS feature_geom_as_geo_json
  FROM asb_feature
    LEFT JOIN asb_user AS asb_create_user ON asb_feature.create_user_id = asb_create_user.user_id
    LEFT JOIN asb_user AS asb_update_user ON asb_feature.update_user_id = asb_update_user.user_id
  ORDER BY asb_feature.feature_id;

CREATE OR REPLACE VIEW asb_remark_view AS
  SELECT
    asb_remark.remark_id                      AS remark_id,
    asb_remark.feature_id                     AS feature_id,
    asb_create_user.user_id                   AS create_user_id,
    asb_create_user.user_alias                AS create_user_alias,
    asb_create_user.user_image_path           AS create_user_image_path,
    asb_create_user.user_tags                 AS create_user_tags,
    asb_remark.remark_message                 AS remark_message,
    asb_remark.remark_image_path              AS remark_image_path,
    asb_remark.remark_reputation              AS remark_reputation,
    asb_remark.remark_reputation_voters_count AS remark_reputation_voters_count,
    asb_remark.remark_create_date             AS remark_create_date
  FROM asb_remark
    LEFT JOIN asb_user AS asb_create_user ON asb_remark.create_user_id = asb_create_user.user_id
  ORDER BY asb_remark.remark_id;
