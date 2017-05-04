#!/usr/bin/env bash

psql -f create.sql
psql -d exchange -f extensions.sql
psql -d exchange -f area_code.sql
psql -d exchange -f switch.sql
