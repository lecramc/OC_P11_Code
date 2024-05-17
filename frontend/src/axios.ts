import axios from "axios";

const defaultOptions = {
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    "Content-Type": "application/json",
    "X-API-KEY": import.meta.env.VITE_API_KEY,
  },
};
console.log(import.meta.env);

const instance = axios.create(defaultOptions);

export default instance;
