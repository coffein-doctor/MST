"use client";

import React from "react";
import Carousel from "@/components/common/Carousel/Carousel";
import Form from "@/components/common/Form/Form";
import HomeFormContent from "@/components/Home/HomeFormContent";

function home() {
  const CAROUSEL_IMAGES = [
    "https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg",
    "https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg",
    "https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg",
  ];

  const DummyData = [];

  return (
    <div>
      <Carousel carouselList={CAROUSEL_IMAGES} />
      <Form content={<HomeFormContent />} />
    </div>
  );
}

export default home;
