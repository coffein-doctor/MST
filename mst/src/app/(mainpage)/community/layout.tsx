"use client";
import { css } from "@emotion/react";

export default function CommunityLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return <div css={communityContentWrapperCSS}>{children}</div>;
}
const communityContentWrapperCSS = css`
  margin: 0px 20px;
`;
