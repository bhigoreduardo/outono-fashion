import { Component, HostListener, OnInit, ViewChild } from '@angular/core';

import { SwiperOptions } from 'swiper';
import { SwiperComponent } from 'swiper/angular';

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.scss']
})
export class IndividualComponent implements OnInit {

  imgTest: string = 'assets/produtos/feminino/roupas/camisas/camisa-armadilho-manga-longa-algodao-branco-5-1.webp';
  imgTest2: string = 'assets/produtos/feminino/roupas/camisas/camisa-armadilho-manga-longa-algodao-branco-5-2.webp';
  imgTest3: string = 'assets/produtos/feminino/roupas/camisas/camisa-armadilho-manga-longa-algodao-branco-5-3.webp';
  imgActive: string = 'assets/produtos/feminino/roupas/camisas/camisa-armadilho-manga-longa-algodao-branco-5-1.webp';

  sideDescription?: boolean;
  sideDetail?: boolean;

  // thumbSlideDesktop!: SwiperOptions;
  // thumbSlideMobile!: SwiperOptions;
  // thumbMobile: boolean = false;

  thumbSlide!: SwiperOptions;
  commentaryGallery!: SwiperOptions;
  mostSaleSlide!: SwiperOptions;

  // @ViewChild('customSwiperGender', { static: false }) customSwiperGender!: SwiperComponent;

  constructor() { }

  ngOnInit(): void {
    this.initializeVars();
  }

  initializeVars() {
    this.sideDescription = false;
    this.sideDetail = false;

    // this.thumbSlideDesktop = {
    //   direction: 'vertical',
    //   slidesPerView: 3,
    //   spaceBetween: 30,
    //   scrollbar: { draggable: true },
    // };

    // this.thumbSlideMobile = {
    //   direction: 'horizontal',
    //   slidesPerView: 3,
    //   spaceBetween: 10,
    //   scrollbar: { draggable: true }
    // }

    // if (window.innerWidth <= 580) {
    //   this.thumbMobile = true;
    // }

    this.thumbSlide = {
      direction: 'horizontal',
      slidesPerView: 3,
      spaceBetween: 10,
      scrollbar: { draggable: true },

      breakpoints: {
        581: {
          direction: 'vertical',
          spaceBetween: 30,
        }
      }
    }

    this.commentaryGallery = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 30,
      scrollbar: { draggable: true },

      breakpoints: {
        510: {
          slidesPerView: 2,
        }
      }
    }

    this.mostSaleSlide = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 30,
      scrollbar: { draggable: true },
      breakpoints: {
        470: {
          slidesPerView: 2,
        },
        730: {
          slidesPerView: 3,
        },
        1100: {
          slidesPerView: 4,
        }
      }
    }
  }

  // @HostListener('window:resize', ['$event'])
  // onWindowResize() {
  //   if (window.innerWidth <= 580) {
  //     this.thumbMobile = true;
  //   } else {
  //     this.thumbMobile = false;
  //   }
  // }

  prevSlide(target: any) {
    target.swiperRef.slidePrev();
    // this.customSwiperGender.swiperRef.slidePrev();
  }

  nextSlide(target: any) {
    target.swiperRef.slideNext();
  }

}
