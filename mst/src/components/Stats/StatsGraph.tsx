import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

function StatsGraph() {
  const data = [
    { name: "Jan", pv: 100 },
    { name: "Feb", pv: 150 },
    { name: "Mar", pv: 50 },
    { name: "Apr", pv: 100 },
  ];

  return (
    <LineChart
      width={300}
      height={400}
      data={data}
      margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" />
      <YAxis />
      <Tooltip />
      {/* <Legend /> */}
      <Line type="monotone" dataKey="pv" stroke="#8884d8" />
    </LineChart>
  );
}

export default StatsGraph;
