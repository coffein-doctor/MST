import { MultiSectionDigitalClock } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";

interface DigitalClockProps {
  selectedTime: Dayjs | null;
  onChange: (date: Dayjs | null) => void;
}

export default function CustomDigitalClock({
  selectedTime,
  onChange,
}: DigitalClockProps) {
  return (
    <MultiSectionDigitalClock
      value={selectedTime}
      onChange={onChange}
      timeSteps={{ minutes: 10 }}
      views={["meridiem", "hours", "minutes"]}
      sx={digitalClockCSS}
    />
  );
}

const digitalClockCSS = {
  // 각 ul 태그 넓이
  "& .MuiList-root": {
    width: "33.3%",
    padding: "0px",
    // scrollbar
    "&::-webkit-scrollbar": {
      display: "none",
    },
    // selected Btn
    "& .Mui-selected ": {
      color: "black",
      backgroundColor: "transparent",
      borderBottom: "1px solid var(--gray-color-2)",
      "&:hover": {
        backgroundColor: "transparent",
      },
    },
  },
  "& .MuiButtonBase-root": {
    width: "100%",
    margin: "0px",
  },
};
