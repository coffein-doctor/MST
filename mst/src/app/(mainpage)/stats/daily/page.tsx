"use client";

import React from "react";
import StatsGraph from "@/components/Stats/StatsGraph";
import { css } from "@emotion/react";
import StatsForm from "@/components/Stats/StatsFormContent";
import Form from "@/components/common/Form/Form";

function statsdaily() {
  const formData = [
    { id: 1, name: "TESTFORM 1" },
    { id: 2, name: "TESTFORM 2" },
    { id: 3, name: "TESTFORM 3" },
  ];

  return (
    <div css={statsDailyWrapperCSS}>
      <StatsGraph />

      {formData.map((formItem) => (
        <Form
          key={formItem.id}
          content={<StatsForm data={formItem} />}
          cssProps={css`
            margin-bottom: 10px;
          `}
          shadow={true}
        />
      ))}
    </div>
  );
}

const statsDailyWrapperCSS = css`
  overflow-y: hidden;
`;

export default statsdaily;
