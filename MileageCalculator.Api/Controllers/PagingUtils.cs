

using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;

namespace MileageCalculator.Api.Controllers
{
    public class PagingUtils 
    {

        private readonly IUrlHelper _urlHelper;    

        public PagingUtils(IUrlHelper urlHelper)
        {
            _urlHelper = urlHelper;
        }


        public List<LinkInfo> GetLinks<T>(PagedList<T> list, String route, IDictionary<string, string> qryParams = null)
        {
            if (qryParams == null) {
                qryParams = new Dictionary<string, string>();
            }

            var links = new List<LinkInfo>();

            if (list.HasPreviousPage) 
            {
                var plqp = new Dictionary<string, string>(qryParams);
                links.Add(CreateLink(route, plqp, list.PreviousPageNumber, 
                           list.PageSize, "previousPage", "GET"));
            }

            var lqp = new Dictionary<string, string>(qryParams);
            links.Add(CreateLink(route, lqp, list.PageNumber, 
                           list.PageSize, "self", "GET"));

            if (list.HasNextPage)
            {
                var nlqp = new Dictionary<string, string>(qryParams);
                links.Add(CreateLink(route, nlqp, list.NextPageNumber, 
                           list.PageSize, "nextPage", "GET"));
            }

            return links;
        }

        private LinkInfo CreateLink(
            string routeName, IDictionary<string, string> qryParams, int pageNumber, int pageSize,
            string rel, string method)
        {
            qryParams.Add("PageNumber", pageNumber.ToString());
            qryParams.Add("PageSize", pageSize.ToString());

            return new LinkInfo
            {
                Href = _urlHelper.Link(routeName, qryParams),
                Rel = rel,
                Method = method
            };
        }

        public string CreateLink(string routeName, IDictionary<string, string> qryParams = null)
        {
            if (qryParams == null) {
                qryParams = new Dictionary<string, string>();
            }
            return _urlHelper.Link(routeName, qryParams);
        }
    }
}