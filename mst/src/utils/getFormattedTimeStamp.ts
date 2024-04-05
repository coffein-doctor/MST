import dayjs from "dayjs";
// 하루이상 차이나면 기존의 시간에서 날짜로 형식 변경
export default function getFormattedTimestamp(timestamp: string): string {
  const currentTime = dayjs();
  const createdTime = dayjs(timestamp);

  return currentTime.diff(createdTime, "day") >= 1
    ? createdTime.format("YY.MM.DD")
    : createdTime.format("HH:mm");
}
