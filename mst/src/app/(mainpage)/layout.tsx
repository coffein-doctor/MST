"use client";

import "@/styles/globals.css";
import Nav from "@/components/common/Nav/Nav";
import { css } from "@emotion/react";

export default function MainPageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div>
      <div css={contentWrapperCSS}>{children}</div>
      <Nav />
    </div>
  );
}

const contentWrapperCSS = css`
  overflow-x: hidden;
  overflow-y: hidden;
`;
