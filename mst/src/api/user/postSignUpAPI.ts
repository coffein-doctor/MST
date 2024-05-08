import { defaultInstance, tokenInstance } from "../instance";
import { SignUpFormData } from "@/types/api/apiRequestTypes";

type bodyType = {
  body: SignUpFormData;
};

type responseType = {
  status: number;
  data: string;
};

export const postSignUpAPI = async ({ body }: bodyType) => {
  try {
    const response: responseType = await tokenInstance.post(
      `user-service/users`,
      body
    );

    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};
