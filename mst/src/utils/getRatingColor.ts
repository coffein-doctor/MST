import { css } from "@emotion/react";

export default function getRatingColor() {
  return (val: number) => {
    const colorMap: { [key: number]: string } = {
      5: "#ff8a00",
      4: "#ff9f2e",
      3: "#ffb55d",
      2: "#ffc888",
      1: "#ffd6a7",
    };

    return css`
      background-color: ${colorMap[val]};
    `;
  };
}
