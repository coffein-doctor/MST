import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";

interface NavCircleContentProps {
  svg: React.ReactNode;
  link: string;
}

function NavCircleContent({ svg, link }: NavCircleContentProps) {
  const router = useRouter();

  return (
    <div
      css={NavCircleCSS}
      onClick={() => {
        router.push(`/${link}`);
      }}
    >
      <div>{svg}</div>
    </div>
  );
}

const NavCircleCSS = css`
  position: absolute;

  top: -20px;

  width: 64px;
  height: 64px;

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: var(--dafault-yellow-color);
  border-radius: 32px;
`;

const svgWrapper = css``;

export default NavCircleContent;
