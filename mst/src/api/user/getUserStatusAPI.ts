import { tokenInstance } from "@/api/instance";


type responseType = {
  status: number;
  data: string;
};

export const getUserStatusAPI = async () => {
  try {
    const response: responseType = await tokenInstance.get(`/users/status`);
    return response.data;
  } catch (error) {
    console.log("error: ", error);
    throw error;
  }
};

