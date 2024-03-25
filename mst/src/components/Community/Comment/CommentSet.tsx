import { css } from "@emotion/react";
import Image from "next/image";
import BrownCircle from "../../../assets/png/BrownCircle.png";

export default function CommentSet() {
  return (
    <div>
      <div>
        <Image src={BrownCircle} alt="프로필사진" css={profileImgCSS} />
      </div>
      <div>
        <div>이닉네임</div>
      </div>
    </div>
  );
}

const profileImgCSS = css`
  width: 45px;
  height: 45px;
`;
