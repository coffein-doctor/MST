import React, { useState, useEffect } from "react";
import { css } from "@emotion/react";
import NavCircleContent from "./NavCircleContent";
import NavSquareContent from "./NavSquareContent";

import { HOME, STATS, PLUS, COMMUNITY, MYPAGE } from "@/assets/icons";

function Nav() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <div css={NavWrapperCSS(windowWidth)}>
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

const NavWrapperCSS = (width: number) => css`
  /* 최하단 고정 */
  position: fixed;
  bottom: 0;

  width: ${width}px; /* 동적으로 화면 가로 크기를 설정합니다. */
  height: 64px;

  background-color: var(--default-back-color);
`;

const NavContentWrapperCSS = css`
  display: flex;
  justify-content: space-around;
  align-items: center; /* 아이템을 수평 가운데로 정렬합니다. */
  height: 100%; /* 부모의 높이에 맞게 설정 */
`;

export default Nav;
