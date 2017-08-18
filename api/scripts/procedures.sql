CREATE OR REPLACE FUNCTION compute_distance(lon1 double precision, lat1 double precision, lon2 double precision, lat2 double precision) returns double precision as $$
    begin
        return (SELECT ST_Distance_sphere(st_makepoint(lon1, lat1),st_makepoint(lon2, lat2)));
    end;
$$ LANGUAGE plpgsql;