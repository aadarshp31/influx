export default class CONSTANTS {
  static readonly API_URL = import.meta.env.REACT_APP_API_URL || 'http://localhost:4000/api';
  static readonly SIGNUP_API = '/auth/signup';
  static readonly LOGIN_API = '/auth/signin';
  static readonly USER_API = '/users';
}