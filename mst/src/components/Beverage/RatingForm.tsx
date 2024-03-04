import React, { useState } from "react";
import { css } from "@emotion/react";
import Button from "../common/Button/Button";

function RatingForm() {
  const [selectedRatingBtn, setSelectedRatingBtn] = useState<number | null>(
    null
  );

  // Btn 선택시
  const handleRatingClick = (val: number) => {
    setSelectedRatingBtn(val);
  };

  // Btn 컬러
  const getRatingBtnColor = (val: number) => {
    const colorMap: { [key: number]: string } = {
      5: "#ff8a00",
      4: "#ff9f2e",
      3: "#ffb55d",
      2: "#ffc888",
      1: "#ffd6a7",
    };

    return css`
      background-color: ${colorMap[val]};
    `;
  };

  return (
    <div>
      <hr css={hrCSS} />
      <div css={ratingInfoCSS}>음료에 대한 평가를 남겨주세요</div>
      <div css={ratingBtnWrapperCSS}>
        {[5, 4, 3, 2, 1].map((val) => (
          <button
            key={val}
            type="button"
            css={[
              ratingBtnCSS,
              selectedRatingBtn === val && selectedRatingBtnCSS,
              getRatingBtnColor(val),
            ]}
            onClick={() => handleRatingClick(val)}
          >
            {val}
          </button>
        ))}
      </div>
      <textarea css={ratingTextareaCSS} />
      {/* <Button>추가하기</Button> */}
    </div>
  );
}

// Evaluation
const hrCSS = css`
  color: var(--gray-color-4);
  margin-bottom: 30px;
`;

const ratingInfoCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h5);
  margin-bottom: 20px;
`;

const ratingBtnWrapperCSS = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

const ratingBtnCSS = css`
  color: var(--default-white-color);
  font-size: var(--font-size-h3);
  font-weight: var(--font-weight-bold);
  width: 45px;
  height: 45px;
  border-radius: 100%;
  background-color: #ff8a00;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

const selectedRatingBtnCSS = css`
  outline: 3px solid var(--default-white-color);
`;

const ratingTextareaCSS = css`
  width: 100%;
  height: 124px;
  border: 1px solid var(--gray-color-4);
  border-radius: 15px;
  padding: 15px;
  resize: none;
  outline: none;
  font-size: var(--font-size-h4);
`;
export default RatingForm;
