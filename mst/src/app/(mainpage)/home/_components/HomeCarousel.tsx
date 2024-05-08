import React, { useState } from "react";
import Carousel from "react-material-ui-carousel";

interface Item {
  item: string;
  description: string;
}

interface HomeCarouselProps {
  items: Item[];
}

const HomeCarousel: React.FC<HomeCarouselProps> = ({ items }) => {
  const [index, setIndex] = useState(0);

  const handleChange = (cur: number, prev: number) => {
    setIndex(cur);
    console.log(cur, prev);
  };

  return (
    <Carousel
      index={index}
      interval={4000}
      animation="slide"
      indicators={false}
      // stopAutoPlayOnHover
      swipe
      NextIcon="next"
      PrevIcon="prev"
    >
      {items.map((item, i) => (
        <div key={i} style={{ width: "100%", height: "100%" }}>
          {/* {item.name} */}
          {item.description}
        </div>
      ))}
    </Carousel>
  );
};

export default HomeCarousel;
