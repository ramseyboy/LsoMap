using System;
using NpgsqlTypes;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace MileageCalculator.Api.Models
{   
     public class AreaCode 
    {
        [Column("id")]
        public int Id { get; set; }

        [Column("geometry")]    
        public PostgisGeometry Geometry { get; set; }

        [Column("npa")]
        public string Npa { get; set; }

        [Column("state")]
        public string State { get; set; }

        [Column("country")]
        public string Country { get; set; }

        [Column("shape_length")]
        public double ShapeLength { get; set; }

        [Column("shape_area")]
        public double ShapeArea { get; set; }

        [Column("total_results")]
        public long TotalResults { get; set; }
    }
}