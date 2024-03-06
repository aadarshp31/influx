import { LoginPayload, SignupPayload } from '@/interfaces/auth';
import API from '@/lib/Api';
import CONSTANTS from '@/lib/CONSTANTS';

/**
 * Class to make api calls to the auth routes of the server.
 * @author @aadarshp31
 */
export default class AuthApi {

  /**
   * Makes a POST request to the signup route of the server.
   * @param payload data for user signup request.
   * @returns response body of the signup request.
   * @throws error message on failure.
   * @author @aadarshp31
   */
  static async signup(payload: SignupPayload) {
    return await new API().post(CONSTANTS.SIGNUP_API, payload);
  }

  /**
   * Makes a POST request to the signin route of the server.
   * @param payload data for user signin request.
   * @returns response body of the signin request.
   * @throws error message on failure.
   * @author @aadarshp31
   */
  static async login(payload: LoginPayload) {
    return await new API().post(CONSTANTS.LOGIN_API, payload);
  }
}
