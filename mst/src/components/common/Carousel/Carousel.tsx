import { TouchEventHandler, useEffect, useRef, useState } from "react";
import { css } from "@emotion/react";

interface Props {
  carouselList: string[];
}

let touchStartX: number;
let touchEndX: number;

const Carousel = ({ carouselList }: Props) => {
  const [currIndex, setCurrIndex] = useState(1);
  const [currList, setCurrList] = useState<string[]>();

  const carouselRef = useRef<HTMLUListElement>(null);

  useEffect(() => {
    if (carouselList.length !== 0) {
      const startData = carouselList[0];
      const endData = carouselList[carouselList.length - 1];
      const newList = [endData, ...carouselList, startData];

      setCurrList(newList);
    }
  }, [carouselList]);

  useEffect(() => {
    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(-${currIndex}00%)`;
    }
  }, [currIndex]);

  const moveToNthSlide = (index: number) => {
    setTimeout(() => {
      setCurrIndex(index);
      if (carouselRef.current !== null) {
        carouselRef.current.style.transition = "";
      }
    }, 500);
  };

  const handleSwipe = (direction: number) => {
    const newIndex = currIndex + direction;

    if (newIndex === carouselList.length + 1) {
      moveToNthSlide(1);
    } else if (newIndex === 0) {
      moveToNthSlide(carouselList.length);
    }

    setCurrIndex((prev) => prev + direction);
    if (carouselRef.current !== null) {
      carouselRef.current.style.transition = "all 0.5s ease-in-out";
    }
  };

  const handleTouchStart: TouchEventHandler<HTMLDivElement> = (e) => {
    touchStartX = e.nativeEvent.touches[0].clientX;
  };

  const handleTouchMove: TouchEventHandler<HTMLDivElement> = (e) => {
    const currTouchX = e.nativeEvent.changedTouches[0].clientX;

    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(calc(-${currIndex}00% - ${
        (touchStartX - currTouchX) * 2 || 0
      }px))`;
    }
  };

  const handleTouchEnd: TouchEventHandler<HTMLDivElement> = (e) => {
    touchEndX = e.nativeEvent.changedTouches[0].clientX;

    if (touchStartX >= touchEndX) {
      handleSwipe(1);
    } else {
      handleSwipe(-1);
    }
  };

  return (
    <div css={containerCSS}>
      <div
        css={carouselWrapper}
        onTouchStart={handleTouchStart}
        onTouchMove={handleTouchMove}
        onTouchEnd={handleTouchEnd}
      >
        <button type="button" onClick={() => handleSwipe(-1)}></button>
        <button type="button" onClick={() => handleSwipe(1)}></button>
        <ul css={carouselCSS} ref={carouselRef}>
          {currList?.map((image, idx) => {
            const key = `${image}-${idx}`;

            return (
              <li key={key} css={carouselItem}>
                <img src={image} alt="carousel-img" />
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
};

const containerCSS = css`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
`;
const carouselWrapper = css`
  position: relative;
  width: 100%;
  /* padding: 0 10%; */
  overflow: hidden;
`;

const carouselCSS = css`
  display: flex;
  width: 100%;
  li {
    flex: none;
    object-fit: contain;
  }
`;

const carouselItem = css`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 200px;
  padding: 10px 0 15px;
  overflow: hidden;
  border-right: 2px solid colors.$WHITE;
  border-left: 2px solid colors.$WHITE;
  transition: border 300ms;
`;

// &:hover {
//   .swipeLeft,
//   .swipeRight {
//     position: absolute;
//     top: 45%;
//     z-index: 1;
//     display: block;
//     padding: 8px 6px;
//     background-color: colors.$GRAYE;
//     border-radius: 10px;
//   }
// }

// .swipeLeft,
// .swipeRight {
//   display: none;
// }

// .swipeLeft {
//   left: 0;
// }

// .swipeRight {
//   right: 0;
// }

//     .carousel {
//       display: flex;
//       width: 100%;

//       li {
//         flex: none;
//         object-fit: contain;
//       }
//     }
//   }

// .carouselItem {
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   width: 100%;
//   height: 350px;
//   padding: 10px 0 15px;
//   overflow: hidden;
//   border-right: 2px solid colors.$WHITE;
//   border-left: 2px solid colors.$WHITE;
//   transition: border 300ms;

//     img {
//       flex-shrink: 0;
//       min-width: 100%;
//       min-height: 100%;
//     }
//   }

//   :root[color-theme='dark'] & {
//     .carouselItem {
//       border-right: 2px solid colors.$GRAY2;
//       border-left: 2px solid colors.$GRAY2;
//     }
//   }
// }

export default Carousel;
