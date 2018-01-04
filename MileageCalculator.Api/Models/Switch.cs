using System;
using NpgsqlTypes;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace MileageCalculator.Api.Models
{
    [Table("switch")]
    public class Switch 
    {

        [Column("id")]
        public int Id { get; set; }

        [Column("geometry")]
        public PostgisGeometry Geometry { get; set; }

        [Column("block")]
        public String Block { get; set; }

        [Column("rate_center")]
        public String RateCenter { get; set; }

        [Column("disc_date")]
        public DateTime? DiscDate { get; set; }

        [Column("ocn")]
        public String Ocn { get; set; }

        [Column("eff_date")]
        public DateTime? EffDate { get; set; }

        [Column("lata")]
        public String Lata { get; set; }

        [Column("area_code")]
        public String AreaCode { get; set; }

        [Column("exchange")]
        public String Exchange { get; set; }

        [Column("switch")]
        public String SwitchId { get; set; }

        [Column("region")]
        public String Region { get; set; }

        [Column("total_results")]
        public long TotalResults { get; set; }
    }
}