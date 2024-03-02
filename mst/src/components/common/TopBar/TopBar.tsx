"use client";
import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";
import { LEFTARROW } from "@/assets/icons";
import { TopBarPropsType } from "@/types/topBarTypes";

function TopBar({ type, content }: TopBarPropsType) {
  const router = useRouter();

  switch (type) {
    case "basic":
      return (
        <div css={topBarWrapperCSS}>
          <div css={backIconWrapperCSS} onClick={() => router.back()}>
            <LEFTARROW color="var(--gray-color-2)" />
          </div>
          <div css={topBarContentCSS}>{content}</div>
        </div>
      );

    case "select":
      return <div>select component but not yet</div>;

    case "search":
      return <div>search component but not yet</div>;
  }
}

const topBarWrapperCSS = css`
  width: 100vw;
  height: 64px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 20px;
`;

const backIconWrapperCSS = css`
  position: absolute;
  left: 20px;
  cursor: pointer;
`;

const topBarContentCSS = css`
  color: var(--gray-color-2);
  font-size: var(--font-size-h3);
`;

export default TopBar;
