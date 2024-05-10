export type ErrorStateType = {
  [key: string]: string;
};

export type ValidationConfigType = {
  required: boolean;

  // 조건
  maxLength?: number;
  minValue?: number;

  // 메시지
  errorMessage?: string;
  emptyMessage?: string;
};
