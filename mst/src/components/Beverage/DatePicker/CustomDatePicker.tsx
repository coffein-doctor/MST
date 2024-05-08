import dayjs, { Dayjs } from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
import { useEffect, useState } from "react";
import { ThemeProvider, createTheme } from "@mui/material";

import { CustomDPTextField } from "./CustomDPTextField";
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

interface CustomDatePickerProps {
  type?: "birthday";
  value: Dayjs | null;
  error: string;
  handleDateChange: (date: Dayjs | null) => void;
}

export default function CustomDatePicker({
  type,
  value,
  error,
  handleDateChange,
}: CustomDatePickerProps) {
  const [datePickerType, setDatePickerType] = useState(false);

  useEffect(() => {
    if (type === "birthday") {
      setDatePickerType(true);
    }
  }, [type]);

  // locale 설정
  dayjs.locale("ko");

  const datePickerCSS = {
    width: "100%",
    marginBottom: "12px",
		"& .MuiOutlinedInput":{

		},
    "& .MuiOutlinedInput-root": {
      color: "var(--default-black-color)",
      borderRadius: "15px",
      outline: error && "1px solid var(--default-red-color)",
      backgroundColor: "var(--default-white-color)",
      height: "40px",
    },
    "& .MuiOutlinedInput-input": {
      color: "var(--default-black-color)",
      textAlign: "center",
      fontSize: datePickerType ? "var(--font-size-h5)" : "var(--font-size-h4)",
    },
  };

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
          value={value}
          onChange={handleDateChange}
          format="YYYY년 MM월 DD일"
          disableFuture
          slots={{
            textField: (props) => (
              <CustomDPTextField
                {...props}
								errorType={error}
                showStartAdornment={datePickerType}
              />
            ),
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
