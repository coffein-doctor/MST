import { defaultInstance } from "@/api/instance";

// type bodyType = {
//     body: {
//         identity: string
//         password: string
//     }
// }

type responseType = {
  status: number;
  data: string;
};

export const postLoginAPI = async (param: string) => {
  try {
    const response: responseType = await defaultInstance.post(
      `/oauth2/authorization/${param}`
    );
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

