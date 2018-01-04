using System;
using MileageCalculator.Api.Models;

namespace MileageCalculator.Api.ViewModels 
{
    public class DistanceResponse
    {
        public Switch Begin { get; private set; }

        public Switch End { get; private set; }

        public double Distance { get; private set; }

        public DistanceResponse(Switch begin, Switch end, double distance) {
            Begin = begin;
            End = end;
            Distance = distance;
        }
    }
}