import React from "react";
import { css } from "@emotion/react";

function HomeFormContent() {
  const dummyData = ["2", "500", "40"];

  return (
    <div css={HomeFormContentWrapper}>
      <div>img</div>
      <div>오늘 물을 {dummyData[0]}L 마셨어요.</div>
      <div>img</div>
      <div>오늘 카페인을 {dummyData[1]}mg 섭취했어요.</div>
      <div>img</div>
      <div>오늘 당을 {dummyData[2]}g 섭취했어요.</div>
    </div>
  );
}

const HomeFormContentWrapper = css`
  display: grid;
  grid-template-columns: 25px 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  column-gap: 10px;
  row-gap: 10px;

  font-size: 0.95rem;
`;

export default HomeFormContent;
