import { tokenInstance } from "@/api/instance";


type responseType = {
  status: number;
  data: string;
};

export const getUserStatusAPI = async () => {
  try {
		console.log()
    const response: responseType = await tokenInstance.get(`/user-service/users/status`);
    return response.data;
  } catch (error) {
    console.log("error: ", error);
    throw error;
  }
};

