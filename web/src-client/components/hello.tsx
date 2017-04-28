import * as React from "react";

export interface HelloProps {
    route: string;
}

export class Hello extends React.Component<HelloProps, undefined> {
    render() {
        return <h1>{this.props.route}</h1>;
    }
}