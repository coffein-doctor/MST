"use client";
import { css } from "@emotion/react";

export default function SignUpLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div>
      <div css={contentWrapperCSS}>{children}</div>
    </div>
  );
}

const contentWrapperCSS = css`
  overflow-x: hidden;
  overflow-y: hidden;
  margin: 0 20px;
`;
