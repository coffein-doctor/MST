import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

function StatsGraph() {
  const data = [
    { time: "0", pv: 30 },
    { time: "3", pv: 150 },
    { time: "6", pv: 400 },
    { time: "6.5", pv: 370 },
    { time: "7", pv: 350 },
    { time: "12" },
    { time: "18" },
    { time: "24" },
  ];

  // const xAxisTicks = ["6", "12", "18", "24"];
  // const interval = Math.floor(data.length / (xAxisTicks.length - 1));

  return (
    <ResponsiveContainer width={320} height={300}>
      <LineChart
        data={data}
        // margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis
          dataKey="time"
          type="number" // 숫자로 설정
          domain={[0, 24]}
        />
        <YAxis />
        <Tooltip />
        {/* <Legend /> */}
        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
      </LineChart>
    </ResponsiveContainer>
  );
}

export default StatsGraph;
