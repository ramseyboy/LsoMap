import * as React from "react";
import PropTypes from "prop-types";
import styles from "./SearchResults.css";

export class SearchResults extends React.Component {

    static propTypes = {
        results: PropTypes.array.isRequired
    };

    static defaultProps = {
        results: []
    };

    render() {
        let results = this.props.results;
        const resultsList = results.map((r) =>
            <li>
                <div className={styles.item}>
                <span className={styles.name} key={r.id}>
                    {r.ocn} - {r.switchId}
                </span>
                </div>
            </li>
        );

        return <ul className={styles.container}>{resultsList}</ul>;
    }
}
