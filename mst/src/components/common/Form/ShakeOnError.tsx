import { css, keyframes } from "@emotion/react";
import { ReactNode, useEffect, useState } from "react";

interface ShakeOnErrorProps {
  isError: boolean;
  children: ReactNode;
}
export default function ShakeOnError({ isError, children }: ShakeOnErrorProps) {
  const [isShaking, setIsShaking] = useState(false);

  useEffect(() => {
    if (isError) {
      setIsShaking(true);
      setTimeout(() => setIsShaking(false), 500);
    }
  }, [isError]);

  return <div css={isShaking && shakeAnimationCSS}>{children}</div>;
}

const shakeKeyframes = keyframes`
	0% { transform: translateX(0); }
	25% { transform: translateX(-3px) }
	50% { transform: translateX(3px) }
	75% { transform: translateX(-3px)  }
	100% { transform: translateX(0); }
`;
const shakeAnimationCSS = css`
  animation: ${shakeKeyframes} 0.5s ease;
`;
