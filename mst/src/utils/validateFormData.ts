import { ErrorStateType, ValidationConfigType } from "@/types/validationTypes";

export default function validateFormData<T>(
  // 검증config
  validationConfig: Record<string, ValidationConfigType>,
  // data
  formdata: T,
  // 검증의 순서
  checkErrorOrder: string[]
) {
  const errors: ErrorStateType = {};

  // 배열로서 검증 순서를 지킴
  for (const key of checkErrorOrder) {
    const config = validationConfig[key];
    let value: unknown = formdata[key as keyof T];
    if (typeof value === "string") {
      value = value.trim();
    }

    if (config.required && value === "") {
      // 비어있는 경우 판단
      errors[key] = config.emptyMessage || "";
      // 바로 return함으로써 오류 하나만 반영(삭제시 여러개 반영 가능)
      return errors;
    } else if (
      // maxLength판단
      typeof value === "string" &&
      config.maxLength !== undefined &&
      value.length > config.maxLength
    ) {
      errors[key] = config.errorMessage || "";
      // 바로 return함으로써 오류 하나만 반영(삭제시 여러개 반영 가능)
      return errors;
    }
  }

  return errors;
}
