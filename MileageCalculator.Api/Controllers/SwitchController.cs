using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MileageCalculator.Api.Models;
using MileageCalculator.Api.Services;
using MileageCalculator.Api.ViewModels;

namespace MileageCalculator.Api.Controllers
{
    [Route("api/[controller]")]
    public class SwitchController : Controller
    {
        private readonly IMappingService _mappingService;
        private readonly PagingUtils _pagingUtils;

        public SwitchController(IMappingService mappingService, PagingUtils pagingUtils)
        {
            _mappingService = mappingService;
            _pagingUtils = pagingUtils;
        }

        [HttpGet(Name="switch")]
        public async Task<IActionResult> AllSwitches([FromQuery] PagingParams pagingParams) {
            var results = await _mappingService.Switches(pagingParams);
            var model = new PagedResponse<Switch>{
                Paging = results.GetHeader(), 
                Links = _pagingUtils.GetLinks(results, "switch"), 
                Items = results.List
            };

            Response.Headers.Add("X-Pagination", results.GetHeader().ToJson());
            Response.Headers.Add("Content-Type", "application/json");
            return Ok(model);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> SwitchById(int id) {
            var results = await _mappingService.SwitchById(id);
            Response.Headers.Add("Content-Type", "application/json");
            return Ok(results);
        }

        [HttpGet("search", Name="switch/search")]     
        public async Task<IActionResult> AreaCodeSearch([FromQuery] string areaCode, [FromQuery] string exchange, [FromQuery] string region, [FromQuery] string switchId, [FromQuery] PagingParams pagingParams) {
            
            Response.Headers.Add("Content-Type", "application/json");
            
            var qryParams = new Dictionary<string, string>();            

            PagedList<Switch> results;
            if (areaCode != null) 
            {
                qryParams.Add("areacode", areaCode);
                results = await _mappingService.SwitchByAreaCode(pagingParams, areaCode);                        
            } else if (exchange != null) {
                qryParams.Add("exchange", exchange);
                results = await _mappingService.SwitchByExchange(pagingParams, exchange);
            } else if (region != null) {
                qryParams.Add("region", region);
                results = await _mappingService.SwitchByRegion(pagingParams, region);
            } else if (switchId != null) {            
                var idResults = await _mappingService.SwitchBySwitchId(switchId);
                return Ok(idResults);
            } else {
                var errorModel = new ErrorResponse(
                    _pagingUtils.CreateLink("switch/search"),
                    "Must provide query parameter of either {areacode}, {exchange}, {region} or {switchId}",
                    "Could not complete your request, change your search and try again"                    
                );
                return BadRequest(errorModel);
            }                    

            var model = new PagedResponse<Switch>{
                Paging = results.GetHeader(), 
                Links = _pagingUtils.GetLinks(results, "switch/search", qryParams),
                Items = results.List
            };
            Response.Headers.Add("X-Pagination", results.GetHeader().ToJson());            
            return Ok(results);
        }
    }
}