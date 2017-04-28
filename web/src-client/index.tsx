import * as React from "react";
import * as ReactDOM from "react-dom";
import {Router, Route} from "react-router";
import {Hello} from "./components/hello";

class Index extends React.Component<undefined, undefined> {
    render() {
        return (<h1>Index</h1>);
    }
}

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={Index}/>
        <Route path="/map" component={<Hello route="Map"/>}/>
        <Route path="/list" component={<Hello route="List"/>}/>
        <Route path="/*" component={<h1>Not Found</h1>}/>
    </Router>,
    document.getElementById("root")
);
