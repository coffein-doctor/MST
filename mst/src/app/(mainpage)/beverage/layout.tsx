"use client";
import BasicTopBar from "@/components/common/TopBar/BasicTopBar";
import { css } from "@emotion/react";
export default function BeverageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <div css={contentWrapperCSS}>{children}</div>
    </>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
`;
