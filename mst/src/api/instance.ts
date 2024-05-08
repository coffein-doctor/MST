import axios from "axios";
import { getCookie, removeCookie } from "./cookie";


const baseUrl = process.env.NEXT_PUBLIC_BASE_URL;
const accessToken = getCookie("access")

// 인증이 필요 없는 기본 요청
export const defaultInstance = axios.create({
  baseURL: baseUrl,
  withCredentials: false,
});

// 인증이 필요한 기본 요청
export const tokenInstance = axios.create({
  baseURL: baseUrl,
  // 임시로 추가
  headers: {
    access: accessToken,
  },
});

defaultInstance.interceptors.response.use(
  (config) => config,
  async (error) => {
    if (error.response.data.code === "29") {
      await removeCookie("Authorization");
    }
    return await Promise.reject(error);
  }
);

// tokenInstance 인터셉터 처리
tokenInstance.interceptors.request.use(
  (config) => {
    // 1. 쿠키 값에서 accesstoken 가져오기
    const accessToken = getCookie("Authorization");
    // 2. accesstoken 있다면 쿠키 값 포함한 채로 http 요청
    if (accessToken) config.headers["Authorization"] = `${accessToken}`;

    return config;
  },
  async (error) => Promise.reject(error)
);

// tokenInstance.interceptors.response.use(
//   (response) => response,
//   async (error) => {
//     console.log("Fewfwefe", error.response.data.code);
//     if (error.response.data.code === "29") {
//       await removeCookie("Authorization");
//     }
//     return await Promise.reject(error);
//   }
// );
