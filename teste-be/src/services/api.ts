import axios from "axios";

export const api = axios.create({
  //baseURL: "http://192.168.15.109:3333",
  baseURL: "http://192.168.15.109:8080/api",
});
