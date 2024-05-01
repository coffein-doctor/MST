export type ErrorStateType = {
  [key: string]: string;
};

export type ValidationConfigType = {
  required: boolean;
  maxLength?: number;

  errorMessage?: string ;
  emptyMessage?: string ;
};
