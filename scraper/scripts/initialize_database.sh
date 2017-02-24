#!/usr/bin/env bash

#psql -f create.sql
#psql -d exchange -f extensions.sql
ogr2ogr -f "PostgreSQL" PG:"dbname=exchange user=exchangeapp" "../resources/collected-switches.json" -nln switches -append
ogr2ogr -f "PostgreSQL" PG:"dbname=exchange user=exchangeapp" "../resources/areacodes.json" -nln area_codes -append
psql -d exchange -f alter_switch_table.sql