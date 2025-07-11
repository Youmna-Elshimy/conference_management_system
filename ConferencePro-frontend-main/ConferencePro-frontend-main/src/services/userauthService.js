import axios from "./axios";
import secureLocalStorage from "react-secure-storage";

const signup = async (user) => {
  return axios({
    method: 'post',
    url: '/users',
    headers: {'Content-Type': 'application/json'},
    data: user
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
    alert('Signup failed. Please try again.');
  })
};

const login = async (user) => {
  return axios({
    method: 'post',
    url: '/users/auth',
    headers: {'Content-Type': 'application/json'},
    data: user
  })
  .then(function (response) {
    console.log(response);
    secureLocalStorage.setItem('user', JSON.stringify(response.data));
  })
  .catch(function (error) {
    console.log(error)
    alert('Login failed. Please try again.');
  })
};

const logout = async () => {
  secureLocalStorage.removeItem('user');
};

const userAuthService = {
  signup,
  login,
  logout
};

export default userAuthService;