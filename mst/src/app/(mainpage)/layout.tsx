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
      TopBarType: "basic" | "select" | "search";
      showTopBar: boolean;
      title?: string;
      showNavBar: boolean;
    };
  }

  const pathname = usePathname();

  const pagesConfig: PagesConfig = {
    "/home": {
      TopBarType: "basic",
      showTopBar: true,
      title: "홈페이지",
      showNavBar: true,
    },
    "/stats.*": {
      TopBarType: "basic",
      showTopBar: false,
      showNavBar: true,
    },
  };

  const currentPageConfig = Object.entries(pagesConfig).find(([key]) => {
    return new RegExp(`^${key.replace("*", ".*")}$`).test(pathname);
  })?.[1];

  return (
    <div>
      {currentPageConfig?.showTopBar && currentPageConfig.title && (
        <TopBar
          content={currentPageConfig.title}
          type={currentPageConfig.TopBarType}
        />
      )}
      <div css={contentWrapperCSS}>{children}</div>
      {currentPageConfig?.showNavBar && <Nav />}
    </div>
  );
}

const contentWrapperCSS = css`
  margin: 0 20px;
  overflow-x: hidden;
`;
