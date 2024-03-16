"use client";

import React, { useEffect, useRef, useState } from "react";
import { css } from "@emotion/react";
import { SUGAR, COFFEE, WATER } from "@/assets/icons";

function Home() {
  const [currIndex, setCurrIndex] = useState(1);
  const [currList, setCurrList] = useState<string[]>();
  const carouselRef = useRef<HTMLUListElement>(null);
  const transitionRef = useRef<boolean>(false); // 추가: 애니메이션 진행 여부를 나타내는 변수

  // const CAROUSEL_IMAGES = [
  //   "https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg",
  //   "https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg",
  //   "https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg",
  // ];

  const CAROUSEL_IMAGES = [SUGAR, COFFEE, WATER];

  useEffect(() => {
    if (CAROUSEL_IMAGES.length !== 0) {
      const startData = CAROUSEL_IMAGES[0];
      const endData = CAROUSEL_IMAGES[CAROUSEL_IMAGES.length - 1];
      const newList = ["0번 인덱스", ...CAROUSEL_IMAGES, startData];

      setCurrList(newList);
    }
  }, [CAROUSEL_IMAGES]);

  useEffect(() => {
    if (carouselRef.current !== null) {
      // 추가: transitionRef 값에 따라 transition을 적용하거나 제거
      carouselRef.current.style.transition = transitionRef.current
        ? "transform 0.5s ease-in-out"
        : "";
      carouselRef.current.style.transform = `translateX(-${currIndex}00%)`;
    }
  }, [currIndex]);

  const moveToNthSlide = (index: number) => {
    setTimeout(() => {
      setCurrIndex(index);
      // 추가: 애니메이션을 제거하기 위해 transitionRef를 false로 설정
      transitionRef.current = false;
    }, 500);
  };

  const handlePrevSlide = () => {
    const newIndex = currIndex - 1;
    if (newIndex === 0) {
      moveToNthSlide(CAROUSEL_IMAGES.length);
    } else {
      setCurrIndex(newIndex);
      // 추가: 애니메이션을 추가하기 위해 transitionRef를 true로 설정
      transitionRef.current = true;
    }
  };

  const handleNextSlide = () => {
    const newIndex = currIndex + 1;
    if (newIndex === CAROUSEL_IMAGES.length + 1) {
      // 3번에서 4번으로 넘어갈 때
      console.log("3 to 4");
      setCurrIndex(newIndex);
      transitionRef.current = true; // 애니메이션 적용

      setTimeout(() => {
        // 0.5초 뒤에 4에서 1로 넘어가기
        setCurrIndex(1);
        transitionRef.current = false; // 애니메이션 제거
      }, 500);
    } else if (newIndex === CAROUSEL_IMAGES.length + 1) {
      // 4번에서 1번으로 넘어갈 때
      console.log("4 to 1");
      setCurrIndex(1); // 첫 번째 인덱스로 이동
      transitionRef.current = false; // 애니메이션 제거
    } else {
      // 그 외의 경우
      setCurrIndex(newIndex);
      transitionRef.current = true; // 애니메이션 적용
    }
  };

  return (
    <div>
      <div css={containerCSS}>
        <div css={carouselWrapper}>
          <button type="button" onClick={handlePrevSlide}>
            Previous
          </button>
          <button type="button" onClick={handleNextSlide}>
            Next
          </button>
          <ul css={carouselCSS} ref={carouselRef}>
            {currList?.map((image, idx) => {
              const key = `${image}-${idx}`;

              return (
                <li key={key} css={carouselItem}>
                  {image}
                  {/* <img src={image} alt="carousel-img" /> */}
                </li>
              );
            })}
          </ul>
        </div>
      </div>
    </div>
  );
}

const containerCSS = css`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
`;
const carouselWrapper = css`
  position: relative;
  width: 100%;
  overflow: hidden;
`;

const carouselCSS = css`
  display: flex;
  width: 100%;
  li {
    flex: none;
    object-fit: contain;
  }
`;

const carouselItem = css`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 200px;
  padding: 10px 0 15px;
  overflow: hidden;
  border-right: 2px solid colors.$WHITE;
  border-left: 2px solid colors.$WHITE;
  transition: border 300ms;
`;

export default Home;
