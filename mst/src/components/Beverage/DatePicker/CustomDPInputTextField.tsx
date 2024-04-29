import {
  InputAdornment,
  TextField,
  TextFieldProps,
  Typography,
} from "@mui/material";
import { CalendarIcon } from "@mui/x-date-pickers";

// DatePicker Modal을 띄우기 전 input
export function CustomDPTextField(
  props: TextFieldProps & { showStartAdornment?: boolean }
) {
  const { showStartAdornment, ...otherProps } = props;
  return (
    <TextField
      {...otherProps}
      InputProps={{
        readOnly: true,
        startAdornment: showStartAdornment ? (
          <InputAdornment position="start" sx={{ position: "relative" }}>
            <Typography sx={labelCSS}>생일</Typography>
          </InputAdornment>
        ) : null,
        endAdornment: (
          <InputAdornment position="end" sx={{ position: "relative" }}>
            <CalendarIcon
              sx={{
                position: "absolute",
                right: "2%",
                color: "var(--gray-color-4)",
              }}
            />
          </InputAdornment>
        ),
      }}
    />
  );
}

const labelCSS = {
  position: "absolute",
  fontSize: "var(--font-size-h5)",
  color: "var(--gray-color-4)",
  paddingLeft: "5px",
};
