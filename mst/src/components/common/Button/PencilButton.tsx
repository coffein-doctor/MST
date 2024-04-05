"use client";
import React from "react";
import { css } from "@emotion/react";
import { useRouter } from "next/navigation";
import { usePathname } from "next/navigation";
import { PENCIL } from "@/assets/svgs";

function PencilButton() {
  const router = useRouter();
  const currentPath = usePathname();

  const toCreatePage = () => {
    const toPath = `${currentPath}/create`;
    router.push(toPath);
  };

  return (
    <button css={registerBtnWrapperCSS} onClick={toCreatePage}>
      {PENCIL}
    </button>
  );
}

const registerBtnWrapperCSS = css`
  height: 50px;
  width: 50px;
  border-radius: 100%;
  background-color: var(--default-yellow-color);
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default PencilButton;
