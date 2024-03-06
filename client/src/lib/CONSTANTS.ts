

export default class CONSTANTS {
  static readonly API_URL = process.env.REACT_APP_API_URL || 'https://localhost:4000/api';
  static readonly SIGNUP_API = '/auth/signup';
  static readonly LOGIN_API = '/auth/signin';
  static readonly USER_API = '/users';
}