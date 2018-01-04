

using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MileageCalculator.Api.Controllers;
using MileageCalculator.Api.Models;
using Npgsql;
using NpgsqlTypes;

namespace MileageCalculator.Api.Services
{
    public class MappingService : IMappingService
    {
        private ExchangeContext DbContext;

        public MappingService(ExchangeContext dbContext) 
        {
            DbContext = dbContext;
        }

        public async Task<AreaCode> AreaCodeById(int id)
        {
            var idParam = new NpgsqlParameter("_id", NpgsqlDbType.Integer)
            {
                Value = id
            };

            return await DbContext.AreaCodes
                .FromSql("SELECT * FROM app.area_code_by_id(@_id)", idParam)
                .SingleAsync();
        }

        public async Task<AreaCode> AreaCodeByNpa(string npa)
        {
            var npaParam = new NpgsqlParameter("_npa", NpgsqlDbType.Text)
            {
                Value = npa
            };

            return await DbContext.AreaCodes
                .FromSql("SELECT * FROM app.area_code_by_npa(@_npa)  ORDER BY npa", npaParam)
                .SingleAsync();
        }

        public async Task<PagedList<AreaCode>> AreaCodeByState(PagingParams page, string state)
        {
            var stateParam = new NpgsqlParameter("_state", NpgsqlDbType.Text)
            {
                Value = state
            };

            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.AreaCodes
                .FromSql("SELECT * FROM app.area_code_by_state(@_state, @_page_size, @_page_number) ORDER BY state", stateParam, sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<AreaCode>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        public async Task<PagedList<AreaCode>> AreaCodes(PagingParams page)
        {
            
            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.AreaCodes
                .FromSql("SELECT * FROM app.area_codes(@_page_size, @_page_number) ORDER BY state", sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<AreaCode>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        public async Task<PagedList<Switch>> SwitchByAreaCode(PagingParams page, string areaCode)
        {
            var areaCodeParam = new NpgsqlParameter("_area_code", NpgsqlDbType.Text)
            {
                Value = areaCode
            };

            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.Switches
                .FromSql("SELECT * FROM app.switch_by_area_code(@_area_code, @_page_size, @_page_number) ORDER BY area_code", areaCodeParam, sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<Switch>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        public async Task<PagedList<Switch>> SwitchByExchange(PagingParams page, string exchange)
        {
            var exchangeParam = new NpgsqlParameter("_exchange", NpgsqlDbType.Text)
            {
                Value = exchange
            };

            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.Switches
                .FromSql("SELECT * FROM app.switch_by_exchange(@_exchange, @_page_size, @_page_number) ORDER BY exchange", exchangeParam, sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<Switch>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        public async Task<Switch> SwitchById(int id)
        {
            var idParam = new NpgsqlParameter("_id", NpgsqlDbType.Integer)
            {
                Value = id
            };

            return await DbContext.Switches
                .FromSql("SELECT * FROM app.switch_by_id(@_id)", idParam)
                .SingleAsync();
        }

        public async Task<PagedList<Switch>> SwitchByRegion(PagingParams page, string region)
        {
            var regionParam = new NpgsqlParameter("_region", NpgsqlDbType.Text)
            {
                Value = region
            };

            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.Switches
                .FromSql("SELECT * FROM app.switch_by_region(@_region, @_page_size, @_page_number) ORDER BY region", regionParam, sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<Switch>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        public async Task<Switch> SwitchBySwitchId(string switchId)
        {
            var switchIdParam = new NpgsqlParameter("_switch_id", NpgsqlDbType.Text)
            {
                Value = switchId
            };

            return await DbContext.Switches
                .FromSql("SELECT * FROM app.switch_by_switch_id(@_switch_id)", switchIdParam)
                .SingleAsync();
        }

        public async Task<PagedList<Switch>> Switches(PagingParams page)
        {
            var sqlPagingParams = CreateSqlPagingParameters(page);

            var results = await DbContext.Switches
                .FromSql("SELECT * FROM app.switches(@_page_size, @_page_number) ORDER BY exchange", sqlPagingParams[0], sqlPagingParams[1])
                .ToListAsync();
            
            return new PagedList<Switch>(results, page.PageNumber, page.PageSize, results.FirstOrDefault().TotalResults);
        }

        private NpgsqlParameter[] CreateSqlPagingParameters(PagingParams page)
        {
            var pageSizeParam = new NpgsqlParameter("_page_size", NpgsqlDbType.Integer)
            {
                Value = page.PageSize
            };

            var pageNumberParam = new NpgsqlParameter("_page_number", NpgsqlDbType.Integer)
            {
                Value = page.PageNumber
            };

            return new NpgsqlParameter[]{pageSizeParam, pageNumberParam};
        }

        private async Task<int> RowCount(string tableName)
        {
            var tableNameParam = new NpgsqlParameter("_table_name", NpgsqlDbType.Text)
            {
                Value = tableName
            };
            using (var cmd = DbContext.Database.GetDbConnection().CreateCommand())
            {            
                cmd.CommandText = "select app.count_approx(@_table_name);";
                cmd.Parameters.Add(tableNameParam);            
                DbContext.Database.OpenConnection();
                return (int) await cmd.ExecuteScalarAsync();
            }
        }
    }
}