"use client";

import "@/styles/globals.css";
import Nav from "@/components/common/Nav/Nav";
import { css } from "@emotion/react";
import { usePathname } from "next/navigation";

export default function MainPageLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const pathname = usePathname();

  const shouldRenderNav = () => {
    if (pathname.startsWith("/signup")) {
      return false;
    }
    if (pathname.startsWith("/beverage")) {
      return false;
    }
    if (
      pathname.startsWith("/community/") &&
      !pathname.startsWith("/community/feed")
    ) {
      return false;
    }
    return true;
  };

  return (
    <div>
      <div css={contentWrapperCSS}>{children}</div>
      {/* {shouldRenderNav() && <Nav />} */}
    </div>
  );
}

const contentWrapperCSS = css`
  overflow-x: hidden;
  overflow-y: hidden;
`;
