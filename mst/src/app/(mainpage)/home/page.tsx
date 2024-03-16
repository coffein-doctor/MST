"use client";

import { useState } from "react";
import { css } from "@emotion/react";
import Form from "@/components/common/Form/Form";
import HomeFormContent from "@/components/Home/HomeFormContent";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import Carousel from "react-material-ui-carousel";

import HomeCarousel from "./_components/HomeCarousel";

function Home() {
  const [index, setIndex] = useState(0);

  const handleChange = (cur: number, prev: number) => {
    setIndex(cur);
    console.log(cur, prev);
  };

  function Item({ item }: { item: { description: string } }) {
    return (
      <div style={{ width: "100%", height: "100%" }}>{item.description}</div>
    );
  }

  var items = [
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
      <BasicTopBar content={"홈페이지"} />

      <Form content={<HomeFormContent />} />

      <HomeCarousel items={items} />
    </div>
  );
}

export default Home;
