"use client";
import { css, keyframes } from "@emotion/react";

// export default function LogoTest() {
//   return (
//     <div css={logoWrapperCSS}>
//       <LoadingLogo />
//     </div>
//   );
// }

// const logoWrapperCSS = css`
//   height: 300px;
//   display: flex;
//   justify-content: center;
//   align-items: center;
// `;

const crawlAnimation = keyframes`
  0% {
		transform: translateY(-100%) rotateX(0deg) scale(1);
  
  }
  100% {
		transform: translateY(100%) rotateX(90deg) scale(2);
  }
`;

const openingCrawl = css`
  perspective: 100px;
  transform-style: preserve-3d;
  position: relative;
  animation: ${crawlAnimation} 5s linear infinite;
`;

const crawlText = css`
  position: relative;
  color: black;
  font-size: 24px;
  line-height: 1.5;
  /* transform-origin: 50% 100%; */
`;

const StarWarsOpeningCrawl = () => {
  return (
    <div css={openingCrawl}>
      <div css={crawlText}>
        <p>It is a period of civil war.</p>
        <p>Rebel spaceships, striking</p>
        <p>from a hidden base, have won</p>
        <p>their first victory against</p>
        <p>the evil Galactic Empire.</p>
      </div>
    </div>
  );
};

export default StarWarsOpeningCrawl;
