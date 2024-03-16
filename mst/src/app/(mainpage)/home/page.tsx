"use client";

import { css } from "@emotion/react";
import Form from "@/components/common/Form/Form";
import HomeFormContent from "@/components/Home/HomeFormContent";

function Home() {
  return (
    <div>
      <Form content={<HomeFormContent />} />
    </div>
  );
}

export default Home;
