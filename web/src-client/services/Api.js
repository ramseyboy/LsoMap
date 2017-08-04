import axios from 'axios';

class Api {

    constructor() {
        this.httpClient = axios.create({
            baseURL: 'http://localhost:8181/api/',
            timeout: 10000,
            withCredentials: true,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }

    search(query) {
        return this.httpClient.get(`switch?area_code=${query}`)
    }
}

export default Api;
