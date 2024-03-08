import dayjs, { Dayjs } from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
import { useState } from "react";
import { ThemeProvider, createTheme } from "@mui/material";

import { CustomDPTextField } from "./CustomDPInputTextField";
import CustomDatePickerToolbar from "./CustomDPToolbar";
import CustomDatePickerActionbar from "./CustomDPActionbar";
import "dayjs/locale/ko";

const theme = createTheme({
  typography: {
    fontFamily: "SCoreDream",
  },
});
// https://mui.com/x/react-date-pickers/custom-components/
// https://mui.com/x/api/date-pickers/date-picker/#slots

export default function CustomDatePicker() {
  // locale 설정
  dayjs.locale("ko");

  const [selectedDate, setSelectedDate] = useState<Dayjs | null>(dayjs());

  const handleDateChange = (date: Dayjs | null) => {
    setSelectedDate(date);
  };

  // const tempDate = dayjs(selectedDate).format("YYYY-MM_DD");
  // console.log(tempDate);
  return (
    <ThemeProvider theme={theme}>
      <LocalizationProvider
        //날짜 라이브러리 Day.js 활용
        dateAdapter={AdapterDayjs}
        adapterLocale="ko"
        localeText={{
          datePickerToolbarTitle: "날짜를 선택해주세요",
        }}
      >
        <MobileDatePicker
          name="date"
          value={selectedDate}
          onChange={handleDateChange}
          format="YYYY년 MM월 DD일"
          disableFuture
          slots={{
            textField: CustomDPTextField,
            toolbar: CustomDatePickerToolbar,
            actionBar: CustomDatePickerActionbar,
          }}
					
          slotProps={{
            // selected될 시 색변화
            day: {
              sx: {
                "&.MuiPickersDay-root.Mui-selected": {
                  backgroundColor: "var(--default-yellow-color)",
                },
              },
            },
          }}
          sx={datePickerCSS}
        />
      </LocalizationProvider>
    </ThemeProvider>
  );
}

const datePickerCSS = {
  width: "100%",
  marginBottom: "12px",
  "& .MuiOutlinedInput-root": {
    color: "var(--default-black-color)",
    borderRadius: "15px",
    backgroundColor: "var(--default-white-color)",
    height: "40px",
  },
  "& .MuiOutlinedInput-input": {
    color: "var(--default-black-color)",
    fontSize: "var(--font-size-h4)",
    textAlign: "center",
  },
};
