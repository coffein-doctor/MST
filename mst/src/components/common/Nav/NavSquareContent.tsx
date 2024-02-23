import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";

interface NavSquareContentProps {
  svg?: React.ReactNode;
  title?: string;
  link?: string;
}

function NavSquareContent({ svg, title, link }: NavSquareContentProps) {
  const router = useRouter();

  return (
    <div
      css={NavSquareCSS}
      onClick={() => {
        router.push(`/${link}`);
      }}
    >
      <div css={svgWrapper}>{svg}</div>
      <div css={titleWrapper}>{title}</div>
    </div>
  );
}

const NavSquareCSS = css`
  width: 20%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const svgWrapper = css`
  margin-bottom: 4px;
`;

const titleWrapper = css`
  font-size: var(--font-size-h6);
`;

export default NavSquareContent;
