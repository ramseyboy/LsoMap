

using System;
using System.Threading.Tasks;
using MileageCalculator.Api.Controllers;
using MileageCalculator.Api.Models;

namespace MileageCalculator.Api.Services
{
    public interface IMappingService
    {
        Task<PagedList<AreaCode>> AreaCodes(PagingParams page);
        Task<AreaCode> AreaCodeById(int id);
        Task<PagedList<AreaCode>> AreaCodeByState(PagingParams page, String state);
        Task<AreaCode> AreaCodeByNpa(String npa);
        Task<PagedList<Switch>> Switches(PagingParams page);
        Task<PagedList<Switch>> SwitchByRegion(PagingParams page, String region);
        Task<PagedList<Switch>> SwitchByAreaCode(PagingParams page, String areaCode);
        Task<PagedList<Switch>> SwitchByExchange(PagingParams page, String exchange);
        Task<Switch> SwitchById(int id);
        Task<Switch> SwitchBySwitchId(String switchId);
    }
}