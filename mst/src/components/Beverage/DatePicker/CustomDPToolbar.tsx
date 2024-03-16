import { Box } from "@mui/material";
import { DatePickerToolbar, DatePickerToolbarProps } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";

// DatePicker Modal 상단
export default function CustomDatePickerToolbar(
  props: DatePickerToolbarProps<Dayjs>
) {
  return (
    <Box className={props.className}>
      <DatePickerToolbar
        toolbarFormat="YYYY년 MM월 DD일"
        toolbarPlaceholder="값이 없습니다"
        sx={{
          backgroundColor: "var(--default-back-color)",
          "& .MuiTypography-overline": {
            color: "var(--default-caffein-color)",
            fontSize: "var(--font-size-h6)",
          },
          "& .MuiDatePickerToolbar-title": {
            color: "var( --gray-color-1)",
            fontSize: "var(--font-size-h2)",
          },
        }}
        {...props}
      />
    </Box>
  );
}
