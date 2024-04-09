"use client";

import { useState } from "react";
import { css } from "@emotion/react";
import Form from "@/components/common/Form/Form";

import HomeFormContent from "./_components/HomeFormContent";
import HomeCarousel from "./_components/HomeCarousel";

import { LEFTARROW, RIGHTARROW } from "@/assets/svgs";
import HomeSpeechBubble from "./_components/HomeSpeechBubble";

function Home() {
  const [date, setDate] = useState(new Date());

  const today = new Date();

  const handleDateChange = (amount: number) => {
    const newDate = new Date(date);
    newDate.setDate(newDate.getDate() + amount);
    setDate(newDate);
  };

  const formatDate = (date: Date): string => {
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(yesterday.getDate() - 1);

    const month = date.getMonth() + 1;
    const day = date.getDate();

    if (date.toDateString() === today.toDateString()) {
      return "오늘";
    } else if (date.toDateString() === yesterday.toDateString()) {
      return "어제";
    } else {
      return `${month}월 ${day}일`;
    }
  };

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
      <div css={dateWrapperCSS}>
        <div onClick={() => handleDateChange(-1)}>
          <LEFTARROW
            color="var(--default-black-color)"
            width="15"
            height="24"
          />
        </div>
        <div css={dateFontCSS}>{formatDate(date)}</div>
        <div
          onClick={() => {
            if (date.toDateString() !== today.toDateString()) {
              handleDateChange(1);
            }
          }}
        >
          <RIGHTARROW
            color={
              date.toDateString() === today.toDateString()
                ? "var(--gray-color-3)"
                : "var(--default-black-color)"
            }
            width="15"
            height="24"
          />
        </div>
      </div>

      <div css={dateSpeechBubbleCSS}>
        <HomeSpeechBubble />
      </div>

      <HomeCarousel items={items} />

      <Form content={<HomeFormContent />} />
    </div>
  );
}

const dateWrapperCSS = css`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const dateFontCSS = css`
  font-size: 1.6rem;
`;

const dateSpeechBubbleCSS = css`
  text-align: center;
`;

export default Home;
