import { css, keyframes } from "@emotion/react";
import LoadingLogo from "./LoadingLogo";

const Loading = () => {
  return (
    <div css={{ width: "100%" }}>
      <div css={temp1}>
        <LoadingLogo />
        <div
          css={[floatTextCSS, { textAlign: "right", left: "15%", top: "-50%" }]}
        >
          이것저것 <br />
          마셔따
        </div>
        <div
          css={[
            floatTextCSS,
            {
              animationDelay: "0.5s",
              textAlign: "left",
              left: "60%",
              top: "-100%",
            },
          ]}
        >
          끊임없이
          <br /> 마셔따
        </div>
        <div
          css={[
            floatTextCSS,
            { animationDelay: "1s", left: "60%", top: "100%", zIndex: "50" },
          ]}
        >
          drink..였따
        </div>
      </div>
      {/* <div css={simpleTextCSS}>로딩중입니다</div> */}

      {/* 임시 */}
      {/* <div css={applyAnimation}>
        <div css={temp3}>이것저것 마셔따</div>
        <div css={temp3}>끊임없이 마셔따</div>
        <div css={temp3}>drink..였따</div>
      </div> */}
    </div>
  );
};

const temp1 = css`
  display: flex;
  justify-content: center;
  align-items: center;
  //추가
  position: relative;
`;

const textAnimation = keyframes`
  0% {
    opacity: 0;
    transform: rotate(0deg) translate3d(0px, 20px, 0px);
  }
	20% {
		opacity:0.5;
	}
	80% {
		opacity:0.8
	}
  100% {
    opacity: 0;
    transform:  rotate(360deg) translate3d(0x, 0px, 0px) rotate(-360deg);
	}
`;

const expandFromBehind = keyframes`
	20% {
		opacity:0.8;
  }

  80% {
		opacity:0.8;
		transform: translateY(150px) translateZ(300px) rotateX(80deg) 
  }
	90% {
		opacity:0;
		transform: translateY(160px) translateZ(300px) rotateX(80deg) 
  }
`;

const temp2 = css`
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 20px 0px;
  font-size: var(--font-size-h4);
`;

const floatTextCSS = css`
  ${temp2}
  opacity: 0;
  animation: ${textAnimation} 6s ease-in infinite;
  font-size: var(--font-size-h5);
  font-weight: var(--font-weight-medium);
  animation-delay: 0.2s;
  position: absolute;
  line-height: 1.5rem;
`;

const applyAnimation = css`
  margin: 20px 0px;
  perspective: 450px;

  &::after {
    content: "";
    position: absolute;
    top: 0px;
    left: -50%;
    width: 200%;
    height: 200%;
    background: linear-gradient(to top, transparent, var(--default-back-color));
  }
`;

const temp3 = css`
  opacity: 0;
  animation: ${expandFromBehind} 10s linear infinite;
  translate: translateY(-200px) translateZ(-200px) rotateX(30deg);
  font-size: var(--font-size-h4);
  animation-delay: 0.2s;
  line-height: 1.5rem;
  text-align: center;
  z-index: -2;
`;

const simpleTextCSS = css`
  margin-top: 30px;
  text-align: center;
`;

export default Loading;
