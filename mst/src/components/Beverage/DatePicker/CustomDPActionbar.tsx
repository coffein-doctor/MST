import { Button, DialogActions } from "@mui/material";
import { PickersActionBarProps } from "@mui/x-date-pickers";

// DatePicker Modal 하단 버튼
const datePickerButtonStyle = {
  color: "var(--default-black-color)",
  borderRadius: "15px",
};
export default function CustomDatePickerActionbar(
  props: PickersActionBarProps
) {
  const { onAccept, onCancel } = props;

  return (
    <DialogActions className={props.className}>
      <Button onClick={onCancel} sx={datePickerButtonStyle}>
        취소
      </Button>
      <Button onClick={onAccept} sx={datePickerButtonStyle}>
        확인
      </Button>
    </DialogActions>
  );
}
