FROM postgres:10.6-alpine
COPY 01-dba-postgres.sql /docker-entrypoint-initdb.d/
COPY 02-create-table.sql /docker-entrypoint-initdb.d/
#COPY 03-seed-table.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_USER=harvest_app
ENV POSTGRES_PASSWORD=harvest_app
ENV POSTGRES_DB=harvestdb
