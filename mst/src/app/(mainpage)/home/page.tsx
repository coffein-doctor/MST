"use client";

import Form from "@/components/common/Form/Form";
import HomeFormContent from "@/components/Home/HomeFormContent";

import HomeCarousel from "./_components/HomeCarousel";
import useUserId from "@/hooks/useUserId";

function Home() {
  const userId = useUserId();
  console.log("userID", userId);

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
      <Form content={<HomeFormContent />} />
      <HomeCarousel items={items} />
    </div>
  );
}

export default Home;
