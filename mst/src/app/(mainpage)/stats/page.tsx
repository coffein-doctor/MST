"use client";

import React from "react";
import StatsGraph from "@/components/Stats/StatsGraph";
import { css } from "@emotion/react";

function stats() {
  return (
    <div css={testCSS}>
      <StatsGraph />
    </div>
  );
}

const testCSS = css`
  overflow-x: hidden;
  overflow-y: hidden;
`;

export default stats;
