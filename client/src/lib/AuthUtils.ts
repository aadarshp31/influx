import CONSTANTS from "./CONSTANTS";

/**
 * Contains static methods to handle client side auth related utility classes
 * @author aadarshp31
 */
export default class AuthUtils {

  /**
   * Gets data from jwt token string
   * @param jwt jwt token string
   * @returns decoded jwt token data
   * @author aadarshp31
   */
  static jwtDecode(jwt: string) {
    let token: {
      raw: string, header: { alg: string }, payload: {
        exp: number, iat: number, roles: string, sub: string
      }
    } = {
      raw: jwt,
      header: JSON.parse(window.atob(jwt.split('.')[0])),
      payload: JSON.parse(window.atob(jwt.split('.')[1]))
    }
    return (token)
  }

  /**
   * Checks whether user is logged in or not in the client side
   * @returns boolean indicating whether the user is logged in or not
   * @author aadarshp31
   */
  static isLoggedIn(): boolean {
    try {
      let token: string | null = localStorage.getItem(CONSTANTS.AUTH_TOKEN_KEY);

      if (token === null) {
        return false;
      }

      const parsedToken = AuthUtils.jwtDecode(JSON.parse(token));

      if (parsedToken.payload.exp) {
        const expiryTime = new Date(parsedToken.payload.exp * 1000).getTime();

        if (expiryTime < new Date().getTime()) {
          return false;
        }
      }

      return true;
    } catch (error) {
      return false;
    }
  }

  /**
   * Logs out the user at the client side
   * @author aadarshp31
   */
  static logout() {

    let token: string | null = localStorage.getItem(CONSTANTS.AUTH_TOKEN_KEY);
    let user: string | null = localStorage.getItem(CONSTANTS.USER_KEY);

    if (token !== null) {
      localStorage.removeItem(CONSTANTS.AUTH_TOKEN_KEY);
    }

    if (user !== null) {
      localStorage.removeItem(CONSTANTS.USER_KEY);
    }

    location.pathname = "/";
  }
}