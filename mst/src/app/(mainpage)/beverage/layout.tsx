"use client";
import { showNavBarState } from "@/recoil/atoms/showNavBarState";
import { useSetRecoilState } from "recoil";
export default function CommunitySelectBarLayout({
  children,
}: {
  children: React.ReactNode;
}) {

  // const setShowNavbar = useSetRecoilState(showNavBarState);
  // setShowNavbar(false);
	
  return (
    <>
      <div>{children}</div>
    </>
  );
}
