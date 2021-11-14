alter table candidate add column if not exists geom geometry;
update candidate set geom = st_setsrid(st_makepoint(longitude, latitude), 4326) where latitude is not null and longitude is not null;
