using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace MileageCalculator.Api.Models 
{
    public class ExchangeContext : DbContext
    {
        public ExchangeContext(DbContextOptions<ExchangeContext> options) : base(options) { }

        public DbSet<Switch> Switches { get; set; }
        
        public DbSet<AreaCode> AreaCodes { get; set; }
    }
}
