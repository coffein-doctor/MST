"use client";
import { css } from "@emotion/react";

import BasicHR from "@/components/Beverage/BasicHR";

interface BeverageParams {
  params: { id: string };
}

function BeverageDetail({ params: { id } }: BeverageParams) {
  return (
    <div>
      <div >dff</div>
      <BasicHR />
      <form>df</form>
    </div>
  );
}



export default BeverageDetail;
