import { css, keyframes } from "@emotion/react";
import { useEffect, useState } from "react";

export default function LoadingLogo() {
  const width = "0.1rem";
  const [bevColor, setBevColor] = useState("var(--default-water-color)");

  useEffect(() => {
    const colors = [
      "var(--default-sugar-color)",
      "var(--default-caffein-color)",
      "var(--default-water-color)",
    ];
    const randomColor = colors[Math.floor(Math.random() * colors.length)];
    setBevColor(randomColor);
  }, []);

  return (
    <div>
      <div css={logoWrapperCSS}>
        <svg
          width="100"
          height="100"
          viewBox="0 0 100 100"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          css={totalCSS}
        >
          <path
            d="M54.4807 47.1328C54.0327 47.1328 53.662 47.4737 53.4951 47.7604C53.3799 47.9583 52.8168 48.2859 52.6802 48.4737C52.3595 48.9143 51.9673 49.3118 51.5875 49.7143C51.4657 49.8435 51.3819 49.9946 51.2514 50.1198C51.1907 50.1781 50.5655 50.8198 50.5381 50.8963"
            stroke={bevColor}
            strokeWidth="2.5px"
            strokeLinecap="round"
            css={waterMotionCSS}
          />
          <path
            d="M16.0215 24.6372C19.387 24.5019 23.5568 26.1374 25.9769 27.6525C28.0314 28.9388 30.8527 30.5783 32.1025 32.3846C32.5953 33.0968 32.697 33.9581 33.1896 34.681C35.7763 38.4771 42.2007 37.263 47.0098 37.7802C48.5711 37.9481 48.4175 41.6849 48.4872 42.5534C48.5598 43.459 47.5491 44.2907 48.6294 45.0086C49.8164 45.7974 50.789 46.9097 50.3651 48.2273C50.0347 49.2546 48.3215 51.3648 50.1211 51.8364C51.7061 52.2518 54.162 51.3743 55.1589 52.5583C56.0046 53.5628 55.3921 54.8957 56.5263 55.62C57.2744 56.0976 57.6056 56.3526 58.1149 57.0382C59.0591 58.3095 57.6646 59.3675 56.456 60.1303C54.5388 61.3405 52.8935 62.6337 51.0889 63.9016C50.3891 64.3934 49.9816 64.9803 49.2286 65.4396C48.6044 65.8204 47.9494 66.6609 47.843 67.2111C47.6789 68.0598 47.4065 68.3591 46.9683 69.1551C46.4306 70.1315 46.5279 71.3144 46.1838 72.3375C45.6091 74.0462 45.5276 75.82 45.0006 77.5418C44.5617 78.9758 45.2011 92.549 45.3193 94.0221"
            stroke="var(--gray-color-1)"
            strokeWidth={width}
            strokeLinecap="round"
          />
          <path
            d="M92.8739 46.31C92.5614 51.6607 91.3435 54.5573 87.4448 58.6382C87.1802 58.9152 86.7779 59.011 86.4148 58.8888L61.7406 50.5853L55.1926 48.4448C54.7091 48.2867 54.421 47.7895 54.5272 47.2919C56.1669 39.6127 57.8586 35.5327 62.1508 29.9147C62.4473 29.5266 62.9836 29.4153 63.4127 29.6486L92.3569 45.3865C92.6928 45.5691 92.8962 45.9283 92.8739 46.31Z"
            stroke="var(--gray-color-1)"
            strokeWidth={width}
            css={cupCSS}
          />
        </svg>
      </div>
      {/* <div css={logoTextWrapperCSS}>
        <div css={logoTextCSS}>마셔따!</div>
        <div css={[underlineAnimation, { backgroundColor: bevColor }]} />
      </div> */}
    </div>
  );
}
const runtime = 3;

const logoWrapperCSS = css`
  width: 100px;
  height: 100px;
  border: 3px solid var(--gray-color-1);
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const totalZoom = keyframes`
  0% {
    transform: scale(1); 
		transform: rotate(20deg) translateX(10px) ;
  }
  20% {
    transform: scale(2) translateY(-5px) ; 
  }
	100% {    
		transform: scale(2) translateY(-5px) ; 
		}
`;

const rotateAnimation = keyframes`
	0% {
		transform: rotate(10deg) translateX(20px) ;
	}
	20% {
		transform: rotate(0deg) ;
	}
	100% {
		transform: rotate(0deg)  ;
	}
`;

const waterAnimation = keyframes`
	10% {
		opacity: 0; 
    stroke-dashoffset: 6;
	}
  40% {
		opacity: 1; 
    stroke-dashoffset: 0; 
  }
	100% {
		opacity: 1;
	}
`;

const titleAnimation = keyframes`
	40%{
		opacity: 0;
	}
	50% {
		opacity: 1;
		transform: translateY(0px) scale(1)
	}

	95% {
		opacity: 1;
		transform: translateY(0px) scale(1)
	}
	100% {
		opacity: 0;
		transform: translateY(0px) scale(1)
	}
`;

const drawUnderline = keyframes`
  40% {
    width: 0;
  }
  60% {
    width: 50px;
  }
	100% {
    width: 50px;
  }
	
`;

const waterMotionCSS = css`
  opacity: 0%;
  stroke-dasharray: 6;
  animation: ${waterAnimation} ${runtime}s ease-out infinite;
`;

const totalCSS = css`
  animation: ${totalZoom} ${runtime}s ease-in-out infinite;
`;

const cupCSS = css`
  animation: ${rotateAnimation} ${runtime}s ease-in-out infinite;
`;

const logoTextWrapperCSS = css`
  position: relative;
`;

const logoTextCSS = css`
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-bold);
  margin-top: 10px;
  text-align: center;
  opacity: 0;
  transform: translateY(-50px) scale(2);
  color: var(--default-black-color);
  animation: ${titleAnimation} ${runtime}s ease-in-out infinite;
`;

const underlineAnimation = css`
  content: "";
  position: absolute;
  left: 25px;
  bottom: -10px;
  width: 0;
  height: 4px;
  border-radius: 15px;
  animation: ${drawUnderline} ${runtime}s infinite;
`;
