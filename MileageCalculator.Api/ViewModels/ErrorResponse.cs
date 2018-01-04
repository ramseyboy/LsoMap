using System;
using MileageCalculator.Api.Controllers;

namespace MileageCalculator.Api.ViewModels 
{
    public class ErrorResponse
    {
        public string Link { get; private set; }
        public string SystemMsg { get; private set; }
        public string UserMsg { get; private set; }

        public ErrorResponse(string link, string systemMsg, string userMsg) {
            Link = link;
            SystemMsg = systemMsg;
            UserMsg = userMsg;
        }
    }
}
