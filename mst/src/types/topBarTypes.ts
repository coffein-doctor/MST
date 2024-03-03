export type TopBarPropsType = {
  type?: "basic" | "select" | "search";
  content?: string;
  selectOptions?: { value: string; label: string }[]; // 추가: selectOptions prop을 설정
};

export type BasicTopBarProps = {
  content: string;
};

export type SelectTopBarProps = {
  firstCategory: string;
  secondCategory: string;
  // onSelectCategory: (category: "first" | "second") => void;
};

export type SearchTopBarProps = {};
