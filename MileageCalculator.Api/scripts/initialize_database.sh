#!/usr/bin/env bash

psql -f create.sql
psql -d exchange -f extensions.sql
