import { Pipe, PipeTransform } from '@angular/core';
import { IEstoqueModel } from '../model/IEstoque';

@Pipe({
  name: 'ofertaproduto'
})
export class OfertaprodutoPipe implements PipeTransform {

  transform(estoques: IEstoqueModel[], condition: string): number {

    if (condition == 'min') {
      let minValue: number = 0;

      for (let i = 0; i < estoques.length; i++) {
        if (i == 0 || estoques[i].oferta < minValue) {
          minValue = estoques[i].oferta;
        }
      }

      return minValue;
    } else {
      let maxValue: number = 0;

      for (let i = 0; i < estoques.length; i++) {
        if (i == 0 || estoques[i].oferta > maxValue) {
          maxValue = estoques[i].oferta;
        }
      }

      return maxValue;
    }

  }

}
