import { tokenInstance } from "@/api/instance";


type responseType = {
  status: number;
  data: number;
};

export const getUserIdAPI = async () => {
  try {
    const response: responseType = await tokenInstance.get(`/user-service/users`);
    return response.data;
  } catch (error) {
    console.log("error: ", error);
    throw error;
  }
};

