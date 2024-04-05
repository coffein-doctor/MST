"use client";

import "@/styles/globals.css";
import { css } from "@emotion/react";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";

export default function MainPageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div>
      <BasicTopBar content={"홈페이지"} />
      <div css={contentWrapperCSS}>{children}</div>
    </div>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
`;
