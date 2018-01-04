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
    public class AreaCodeController : Controller
    {
        private readonly IMappingService _mappingService;
        private readonly PagingUtils _pagingUtils;

        public AreaCodeController(IMappingService mappingService, PagingUtils pagingUtils)
        {
            _mappingService = mappingService;
            _pagingUtils = pagingUtils;
        }

        [HttpGet(Name="areacode")]
        public async Task<IActionResult> AllAreaCodes([FromQuery] PagingParams pagingParams) {
            var results = await _mappingService.AreaCodes(pagingParams);
            var model = new PagedResponse<AreaCode>{
                Paging = results.GetHeader(), 
                Links = _pagingUtils.GetLinks(results, "areacode"), 
                Items = results.List
            };

            Response.Headers.Add("X-Pagination", results.GetHeader().ToJson());
            Response.Headers.Add("Content-Type", "application/json");
            return Ok(model);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> AreaCodeById(int id) {
            var results = await _mappingService.AreaCodeById(id);
            Response.Headers.Add("Content-Type", "application/json");
            return Ok(results);
        }

        [HttpGet("search", Name="areacode/search")]     
        public async Task<IActionResult> AreaCodeSearch([FromQuery] string state, [FromQuery] string areaCode, [FromQuery] PagingParams pagingParams) {
            
            if (areaCode != null) 
            {
                var results = await _mappingService.AreaCodeByNpa(areaCode);
                
                Response.Headers.Add("Content-Type", "application/json");
                return Ok(results);
            } else if (state != null) {
                var results = await _mappingService.AreaCodeByState(pagingParams, state);
                
                var qryParams = new Dictionary<string, string>();
                qryParams.Add("state", state);

                var model = new PagedResponse<AreaCode>{
                    Paging = results.GetHeader(), 
                    Links = _pagingUtils.GetLinks(results, "areacode/search", qryParams), 
                    Items = results.List
                };

                Response.Headers.Add("X-Pagination", results.GetHeader().ToJson());
                Response.Headers.Add("Content-Type", "application/json");
                return Ok(model);
            } else {
                var errorModel = new ErrorResponse(
                    _pagingUtils.CreateLink("areacode/search"),
                    "Must provide search query in the form of {state} or {areacode}",
                    "Could not complete your request, change your search and try again"                    
                );
                return BadRequest(errorModel);
            }                    
        }
    }
}