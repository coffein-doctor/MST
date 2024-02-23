import React from "react";
import { css } from "@emotion/react";

function Nav() {
  return <div css={NavWrapperCSS}></div>;
}

const NavWrapperCSS = css`
  /* 임시 */
  border: 1px solid black;

  /* 최하단 고정 */
  position: fixed;
  bottom: 0;

  width: 100%;
  height: 64px;
`;

export default Nav;
