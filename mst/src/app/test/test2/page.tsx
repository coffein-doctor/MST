"use client";

import { useState } from "react";

function TEST() {
  const [offset, setOffset] = useState("60%");

  return (
    <div>
      <svg
        width="304"
        height="406"
        viewBox="0 0 304 406"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <defs>
          <linearGradient id="waterGradient" x1="0%" y1="100%" x2="0%" y2="0%">
            <stop offset={offset} stop-color="var(--default-water-color)" />
            <stop offset={offset} stop-color="var(--default-back-color)" />
          </linearGradient>
        </defs>

        <path
          d="M4.00634 283C-18.1145 203 149.933 5.76181 152.137 3C154.342 5.76181 320.656 203 300.268 283C279.9 374.6 155.648 399.049 152.137 403C148.627 399.049 24.3744 374.6 4.00634 283Z"
          fill="url(#waterGradient)"
          stroke="black"
          stroke-width="3"
          stroke-linecap="round"
        />
        <path
          d="M140.5 248C140.5 262.947 136.642 276.408 130.483 286.086C124.313 295.781 115.974 301.5 107 301.5C98.0257 301.5 89.6866 295.781 83.5168 286.086C77.3582 276.408 73.5 262.947 73.5 248C73.5 233.053 77.3582 219.592 83.5168 209.914C89.6866 200.219 98.0257 194.5 107 194.5C115.974 194.5 124.313 200.219 130.483 209.914C136.642 219.592 140.5 233.053 140.5 248Z"
          fill="white"
          stroke="black"
          stroke-width="3"
        />
        <ellipse cx="116" cy="248" rx="26" ry="49" fill="black" />
        <path
          d="M230.5 248C230.5 262.947 226.642 276.408 220.483 286.086C214.313 295.781 205.974 301.5 197 301.5C188.026 301.5 179.687 295.781 173.517 286.086C167.358 276.408 163.5 262.947 163.5 248C163.5 233.053 167.358 219.592 173.517 209.914C179.687 200.219 188.026 194.5 197 194.5C205.974 194.5 214.313 200.219 220.483 209.914C226.642 219.592 230.5 233.053 230.5 248Z"
          fill="white"
          stroke="black"
          stroke-width="3"
        />
        <ellipse cx="206" cy="247" rx="26" ry="49" fill="black" />
      </svg>
    </div>
  );
}

export default TEST;
