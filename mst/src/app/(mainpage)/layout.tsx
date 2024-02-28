"use client";

import { usePathname } from "next/navigation";
import "@/styles/globals.css";
import Nav from "@/components/common/Nav/Nav";
import TopBar from "@/components/common/TopBar/TopBar";
import { css } from "@emotion/react";

export default function MainPageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  interface PagesConfig {
    [key: string]: {
      showTopBar: boolean;
      title: string;
      showNavBar: boolean;
    };
  }

  const pathname = usePathname();

  const pagesConfig: PagesConfig = {
    "/home": {
      showTopBar: true,
      title: "홈페이지",
      showNavBar: true,
    },
    "/stats": {
      showTopBar: true,
      title: "통계",
      showNavBar: true,
    },
  };

  const currentPageConfig = pagesConfig[pathname];

  return (
    <div>
      {currentPageConfig?.showTopBar && (
        <TopBar content={currentPageConfig.title} />
      )}
      <div css={contentWrapperCSS}>{children}</div>
      {currentPageConfig?.showNavBar && <Nav />}
    </div>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
`;
