"use client";

import { usePathname } from "next/navigation";
import "@/styles/globals.css";
import Nav from "@/components/common/Nav/Nav";
import { css } from "@emotion/react";

export default function MainPageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const pathname = usePathname();

  // layout이 필요없는 router주소는 여기에 넣어준다.
  if (pathname === "/" || pathname === "/beverage") {
    return (
      <div>
        <div css={contentWrapperCSS}>{children}</div>
      </div>
    );
  }

  // 그 외의 경우에는 Nav 컴포넌트와 함께 렌더링
  return (
    <div>
      <div css={contentWrapperCSS}>{children}</div>
      <Nav />
    </div>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
`;
