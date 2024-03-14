import axios, { CancelTokenSource } from "axios";
import CONSTANTS from "./CONSTANTS";

/**
 * Class to make api calls with axios and handle token and cancellation.
 * @author @aadarshp31
 */
export default class API {
  origin: string;
  cancellationToken: CancelTokenSource;

  constructor() {
    this.origin = CONSTANTS.API_URL;
    this.cancellationToken = axios.CancelToken.source();
    axios.defaults.headers.common['Content-Type'] = "application/json";
  }

  /**
   * Cancells the request if it is not completed.
   * @author @aadarshp31
   */
  cancelRequest() {
    this.cancellationToken.cancel();
  }

  /**
   * Adds auth token to the authorization header if it exists in the local storage.
   * @author @aadarshp31
   */
  addAuthHeader() {
    const token = localStorage.getItem('token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    }
  };

  /**
   * Makes a GET request to the given path.
   * @param path path of the route to make the GET request
   * @returns response body of the GET request
   * @throws error message on failure
   * @author @aadarshp31
   */
  async get(path: string) {
    const response = await axios.get(`${this.origin}${path}`);
    return response.data;
  }

  /**
   * Makes a POST request to the given path.
   * @param path path of the route to make the POST request
   * @returns response body of the POST request
   * @throws error message on failure
   * @author @aadarshp31
   */
  async post(path: string, payload: any) {
    const response = (await axios.post(`${this.origin}${path}`, payload));
    return response.data;
  }

  /**
   * Makes a PUT request to the given path.
   * @param path path of the route to make the PUT request
   * @returns response body of the PUT request
   * @throws error message on failure
   * @author @aadarshp31
   */
  async put(path: string, payload: any) {
    const response = await axios.put(`${this.origin}${path}`, payload);
    return response.data;
  }

  /**
   * Makes a DELETE request to the given path.
   * @param path path of the route to make the DELETE request
   * @returns response body of the DELETE request
   * @throws error message on failure
   * @author @aadarshp31
   */
  async delete(path: string) {
    const response = await axios.delete(`${this.origin}${path}`);
    return response.data;
  }

}