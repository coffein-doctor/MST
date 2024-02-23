"use client";

import "@/styles/globals.css";
import Nav from "@/components/common/Nav/Nav";
import { css } from "@emotion/react";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <div css={contentWrapperCSS}>{children}</div>
        <Nav />
      </body>
    </html>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
`;
