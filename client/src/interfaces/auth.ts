export interface SignupPayload {
  firstname: string;
  lastname: string;
  email: string;
  username: string;
  password: string;
}

export interface LoginPayload {
  username: string;
  password: string;
}