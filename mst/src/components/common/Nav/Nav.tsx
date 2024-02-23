import React from "react";
import { css } from "@emotion/react";
import NavCircleContent from "./NavCircleContent";
import NavSquareContent from "./NavSquareContent";

import { HOME, STATS, PLUS, COMMUNITY, MYPAGE } from "@/assets/icons";

function Nav() {
  return (
    <div css={NavWrapperCSS}>
      <div css={NavContentWrapperCSS}>
        <NavSquareContent title={"홈"} svg={HOME} link={"home"} />
        <NavSquareContent title={"통계"} svg={STATS} link={"stats"} />
        <NavSquareContent />
        <NavCircleContent svg={PLUS} link={"link"} />
        <NavSquareContent
          title={"커뮤니티"}
          svg={COMMUNITY}
          link={"community"}
        />
        <NavSquareContent title={"내 정보"} svg={MYPAGE} link={"mypage"} />
      </div>
    </div>
  );
}

const NavWrapperCSS = css`
  /* 최하단 고정 */
  position: fixed;
  bottom: 0;

  width: 100%;
  height: 64px;
`;

const NavContentWrapperCSS = css`
  display: flex;
  justify-content: space-around;
  align-items: center; /* 아이템을 수평 가운데로 정렬합니다. */
  height: 100%; /* 부모의 높이에 맞게 설정 */
`;

export default Nav;
