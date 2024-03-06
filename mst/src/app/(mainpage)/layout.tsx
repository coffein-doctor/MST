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
      TopBarType?: "basic" | "select" | "search";
      title?: string;
      showNavBar: boolean;
      selectOptions?: { value: string; label: string }[];
    };
  }

  const pathname = usePathname();

  const pagesConfig: PagesConfig = {
    "/home": {
      showTopBar: true,
      TopBarType: "basic",
      title: "홈페이지",
      showNavBar: true,
    },
    "/stats.*": {
      showTopBar: true,
      TopBarType: "select",
      selectOptions: [
        { value: "/stats/daily", label: "일간" },
        { value: "/stats/monthly", label: "월간" },
      ],
      showNavBar: true,
    },

    "/beverage": {
      showTopBar: false,
      showNavBar: true,
    },

    "/beverage.*": {
      showTopBar: false,
      showNavBar: true,
    },

    "/community": {
      showTopBar: true,
      TopBarType: "select",
      selectOptions: [
        { value: "/community", label: "커뮤니티" },
        { value: "/community/feed", label: "피드" },
      ],
      showNavBar: true,
    },

    "/community.*": {
      showTopBar: true,
      TopBarType: "select",
      selectOptions: [
        { value: "/community", label: "커뮤니티" },
        { value: "/community/feed", label: "피드" },
      ],
      showNavBar: true,
    },

    "/mypage": {
      showTopBar: false,
      showNavBar: true,
    },
  };

  const currentPageConfig = Object.entries(pagesConfig).find(([key]) => {
    return new RegExp(`^${key.replace("*", ".*")}$`).test(pathname);
  })?.[1];

  return (
    <div>
      {currentPageConfig?.showTopBar && (
        <TopBar
          content={currentPageConfig.title}
          type={currentPageConfig.TopBarType}
          selectOptions={currentPageConfig.selectOptions}
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
