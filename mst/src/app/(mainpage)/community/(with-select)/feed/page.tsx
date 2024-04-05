"use client";
import React, { useState } from "react";
import { css } from "@emotion/react";
import FeedForm from "@/components/Community/FeedForm";
import Form from "@/components/common/Form/Form";
import getRatingColor from "@/utils/getRatingColor";

interface FeedData {
  id: number;
  beverageName: string;
  manufacturer: string;
  userName: string;
  content: string;
  ratingNum: number;
}
const RATING_VALUES = [5, 4, 3, 2, 1];

const dummyData: FeedData[] = [
  {
    id: 1,
    beverageName: "밀크쉐이크쉐이크쉐이크쉐이크",
    manufacturer: "컴포즈커피",
    userName: "대충닉네임2",
    content:
      "여기 밀크쉐이크 도장깨기 하는 중 다 맛있다 여기 밀크쉐이크 도장깨기 하는 중 다 맛있다 여기 밀크쉐이크 도장깨기 하는 중 다 맛있다 여기 밀크쉐이크 도장깨기 하는 중 다 맛있다 여기 밀크쉐이크 도장깨기 하는 중 다 맛있다",
    ratingNum: 5,
  },
  {
    id: 2,
    beverageName: "테스트 타이틀",
    manufacturer: "테스트 제조사",
    userName: "테스트 유저",
    content: "이것은 테스트 데이터입니다. 여기에는 임의의 내용이 들어갑니다.",
    ratingNum: 4,
  },
  {
    id: 3,
    beverageName: "다른 제품",
    manufacturer: "다른 제조사",
    userName: "다른 유저",
    content:
      "다른 제품에 대한 더미 데이터입니다. 여러분은 이것을 자유롭게 변경할 수 있습니다.",
    ratingNum: 3,
  },
  {
    id: 234,
    beverageName: "다른 제품",
    manufacturer: "다른 제조사",
    userName: "다른 유저",
    content:
      "다른 제품에 대한 더미 데이터입니다. 여러분은 이것을 자유롭게 변경할 수 있습니다.",
    ratingNum: 3,
  },
  {
    id: 45645,
    beverageName: "다른 제품",
    manufacturer: "다른 제조사",
    userName: "다른 유저",
    content:
      "다른 제품에 대한 더미 데이터입니다. 여러분은 이것을 자유롭게 변경할 수 있습니다.",
    ratingNum: 3,
  },
];

function Feed() {
  const [selectedRatingPill, setSelectedRatingPill] = useState<number | null>();

  const getRatingPillColor = getRatingColor();

  const handlePillClick = (val: number) => {
    setSelectedRatingPill(val);
  };

  return (
    <div>
      <div css={communityContentCSS}>
        팔로우한 사람이 마신 음료를 확인하세요
      </div>
      {/* 상단 Pill */}
      <div css={pillBtnWrapperCSS}>
        {RATING_VALUES.map((val) => (
          <button
            key={val}
            type="button"
            css={[
              getRatingPillColor(val),
              ratingPillCSS,
              selectedRatingPill === val && selectedRatingPillCSS,
            ]}
            onClick={() => handlePillClick(val)}
          >
            {val}점
          </button>
        ))}
      </div>
      {/* 하단 피드 */}
      <div>
        {dummyData.length === 0 ? (
          <div css={emptyFeedTextCSS}>즐겨찾기한 음료가 없습니다</div>
        ) : (
          dummyData?.map((item) => (
            <Form
              key={item?.id}
              cssProps={feedFormWrapperCSS}
              content={
                <FeedForm
                  beverageName={item?.beverageName}
                  manufacturer={item?.manufacturer}
                  userName={item?.userName}
                  content={item?.content}
                  ratingNum={item?.ratingNum}
                />
              }
            />
          ))
        )}
      </div>
      {/* Navbar 여백 */}
      <div css={emptyNavCSS} />
    </div>
  );
}

const communityContentCSS = css`
  font-size: var(--font-size-h6);
  margin-bottom: 20px;
`;

const pillBtnWrapperCSS = css`
  margin-bottom: 20px;
  display: flex;
  align-items: center;
`;

const ratingPillCSS = css`
  width: 55px;
  height: 30px;
  border-radius: 30px;
  color: var(--default-white-color);
  font-size: var(--font-size-h6);
  font-weight: var(--font-weight-bold);
  margin-right: 12px;
`;

const selectedRatingPillCSS = css`
  border: 1px solid var(--gray-color-2);
`;

const feedFormWrapperCSS = css`
  margin-bottom: 20px;
`;

const emptyFeedTextCSS = css`
  color: var(--gray-color-3);
  font-size: var(--font-size-h6);
  height: 60vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const emptyNavCSS = css`
  height: 80px;
`;

export default Feed;
