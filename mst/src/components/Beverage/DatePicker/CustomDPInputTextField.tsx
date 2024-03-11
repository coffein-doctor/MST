import { InputAdornment, TextField, TextFieldProps } from "@mui/material";
import { CalendarIcon } from "@mui/x-date-pickers";

// DatePicker Modal을 띄우기 전 input
export function CustomDPTextField(props: TextFieldProps) {
  return (
    <TextField
      {...props}
      InputProps={{
        readOnly: true,
        endAdornment: (
          <InputAdornment position="end" sx={{ position: "relative" }}>
            <CalendarIcon
              sx={{
                position: "absolute",
                right:"2%",
                color: "var(--gray-color-4)",
              }}
            />
          </InputAdornment>
        ),
      }}
    />
  );
}
