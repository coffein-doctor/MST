
import { getUserIdAPI } from "@/api/user/getUserIdAPI";
import { useState } from "react";

export default function useUserId() {
  const [userId, setUserId] = useState<number | null>(null);

  const getUserId = async () => {
    try {
      const userId = await getUserIdAPI();
      setUserId(userId);
    } catch (error) {
      console.log(error);
    }
  };
	getUserId()

  return userId;
}
