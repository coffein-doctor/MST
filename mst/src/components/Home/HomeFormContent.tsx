import React from "react";
import { css } from "@emotion/react";

import { WATER, SUGAR, COFFEE } from "@/assets/icons";

function HomeFormContent() {
  const dummyData = ["2", "500", "40"];

  return (
    <div css={HomeFormContentWrapper}>
      <div css={circleStyle("var(--default-water-color)")}>{WATER}</div>
      <div>물을 {dummyData[0]}L 마셨어요.</div>
      <div css={circleStyle("var( --default-caffein-color)")}>{COFFEE}</div>
      <div>카페인을 {dummyData[1]}mg 섭취했어요.</div>
      <div css={circleStyle("var(--default-sugar-color)")}>{SUGAR}</div>
      <div>당을 {dummyData[2]}g 섭취했어요.</div>
    </div>
  );
}

const HomeFormContentWrapper = css`
  display: grid;
  grid-template-columns: 40px 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  column-gap: 10px;
  row-gap: 10px;

  font-size: 0.95rem;
  justify-content: center;
  align-items: center;
`;
const circleStyle = (color: any) => css`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 100%;
  width: 40px;
  height: 40px;
  background-color: ${color};

  svg {
    width: 25px;
  }
`;

export default HomeFormContent;
