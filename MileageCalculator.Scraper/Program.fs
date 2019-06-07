open System
open FSharp.Data

let testFile = "./resources/512-test-search.xml"

type NPA = XmlProvider<"""./resources/512-test-search.xml""">


[<EntryPoint>]
let main argv =
           
    let Root = NPA.Parse(testFile)   
    Console.WriteLine Root.Inputdata.Npa

    0
    

