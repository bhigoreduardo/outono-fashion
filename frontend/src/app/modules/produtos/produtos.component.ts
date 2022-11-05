import { Component, OnInit } from '@angular/core';

import SwiperCore, { SwiperOptions } from 'swiper';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

  lines: number[] = [1, 2, 3, 4];

  sideGenero: boolean;
  sideCategoria: boolean;
  sideTipo: boolean;
  sideTamanho: boolean;
  sideCor: boolean;
  sideMarca: boolean;
  sidePreco: boolean;

  sideBars: boolean;

  constructor() {
    this.sideGenero = false;
    this.sideCategoria = false;
    this.sideTipo = false;
    this.sideTamanho = false;
    this.sideCor = false;
    this.sideMarca = false;
    this.sidePreco = false;

    this.sideBars = false;
  }

  ngOnInit(): void {
  }

  config: SwiperOptions = {
    slidesPerView: 3,
    spaceBetween: 50,
    navigation: true,
    pagination: { clickable: true },
    scrollbar: { draggable: true },
  };
  onSwiper(swiper: any) {
    console.log(swiper);
  }
  onSlideChange() {
    console.log('slide change');
  }

}
