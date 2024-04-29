import { SignUpFormData } from "@/types/api/apiRequestTypes";
import { ErrorStateType, ValidationConfigType } from "@/types/validationTypes";

export default function validateFormData(
  validationConfig: Record<string, ValidationConfigType>,
  formdata: SignUpFormData,
  checkErrorOrder: string[]
) {
  const errors: ErrorStateType = {};

  // 배열로서 검증 순서를 지킴
  for (const key of checkErrorOrder) {
    const config = validationConfig[key];
    let value = formdata[key as keyof SignUpFormData];

    if (typeof value === "string") {
      value = value.trim();
    }

    if (config.required && value === "") {
      errors[key] = config.errorMessage;
      // 바로 return함으로써 오류 하나만 반영(삭제시 여러개 반영 가능)
      return errors;
    } else if (
      typeof value === "string" &&
      config.maxLength !== undefined &&
      value.length > config.maxLength
    ) {
      errors[key] = config.errorMessage;
      // 바로 return함으로써 오류 하나만 반영(삭제시 여러개 반영 가능)
      return errors;
    }
  }

  return errors;
}
