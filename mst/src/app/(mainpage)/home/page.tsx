"use client";

import { useEffect, useState } from "react";
import { css } from "@emotion/react";
import Form from "@/components/common/Form/Form";
import HomeFormContent from "@/components/Home/HomeFormContent";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
// import Carousel from "react-material-ui-carousel";

import HomeCarousel from "./_components/HomeCarousel";
import { SUGAR, COFFEE, WATER } from "@/assets/svgs";
import { userIdState } from "@/recoil/atoms/userIdState";
import { useRecoilState, useRecoilValue } from "recoil";
import useUserId from "@/hooks/useUserId";

function Home() {
  const [index, setIndex] = useState(0);
  // const [id, setId] = useRecoilState(userIdState);
  // console.log("현재", id);

  // useEffect(() => {
  //   const temp = async () => {
  //     const userId = await getUserIdAPI();
  //     setId(userId);
  //   };
	// 	temp()
  // }, []);

	// console.log(id)
	const userId = useUserId()
	console.log("userID", userId)

  const items = [
    {
      name: "Random Name #1",
      description: "1 - Probably the most random thing you have ever seen!",
    },
    {
      name: "Random Name #2",
      description: "2- Hello World!",
    },
    {
      name: "Random Name #3",
      description: "3 - Hello World!",
    },
  ];

  return (
    <div>
      {/* <BasicTopBar content={"홈페이지"} /> */}
      <Form content={<HomeFormContent />} />
      <HomeCarousel items={items} />
    </div>
  );
}

export default Home;
