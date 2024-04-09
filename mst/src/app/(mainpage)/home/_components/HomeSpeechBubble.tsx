import React from "react";
import Image from "next/image";
import { css } from "@emotion/react";

import SpeechBubble from "@/assets/png/SpeechBubble.png";

function HomeSpeechBubble() {
  return (
    <div css={HomeSpeechBubbleWrapper}>
      <Image src={SpeechBubble} alt="SpeechBubble" />
      <div css={HomeSpeechBubblePosition}>
        <p css={HomeSpeechBubbleText}>지금 물 안 마시면 3대가 대머리</p>
      </div>
    </div>
  );
}

const HomeSpeechBubbleWrapper = css`
  position: relative;
  display: inline-block;
`;

const HomeSpeechBubblePosition = css`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const HomeSpeechBubbleText = css`
  color: white;
  font-size: 16px;
  text-align: center;
`;

export default HomeSpeechBubble;
