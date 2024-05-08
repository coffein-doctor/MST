import { defaultInstance } from "@/api/instance";

type responseType = {
  status: number;
  data: number;
};

// 수정해야함
export const getBeverageAPI = async () => {
  try {
    const response: responseType = await defaultInstance.get(`/users`);
    return response.data;
  } catch (error) {
    console.log("error: ", error);
    throw error;
  }
};
