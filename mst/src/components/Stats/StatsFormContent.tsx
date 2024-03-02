import React from "react";

function StatsFormContent(props: any) {
  const { data } = props;

  return (
    <div>
      <p>{data.name}</p>
    </div>
  );
}

export default StatsFormContent;
