

using System.Collections.Generic;
using MileageCalculator.Api.Controllers;

namespace MileageCalculator.Api.ViewModels
{
    public class PagedResponse<T>
    {
        public PagingHeader Paging { get; set; }
        public List<LinkInfo> Links { get; set; }
        public List<T> Items { get; set; }
    }
}