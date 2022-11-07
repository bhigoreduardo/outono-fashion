import { Pipe, PipeTransform } from '@angular/core';
import { IEstoque } from '../modules/produtos/model/IEstoque';

@Pipe({
  name: 'priceproduto'
})
export class PriceprodutoPipe implements PipeTransform {

  transform(estoques: IEstoque[], condition: string): number {

    if (condition == 'min') {
      let minValue: number = 0;

      for (let i = 0; i < estoques.length; i++) {
        if (i == 0 || estoques[i].preco < minValue) {
          minValue = estoques[i].preco;
        }
      }

      return minValue;
    } else {
      let maxValue: number = 0;

      for (let i = 0; i < estoques.length; i++) {
        if (i == 0 || estoques[i].preco > maxValue) {
          maxValue = estoques[i].preco;
        }
      }

      return maxValue;
    }
    
  }

}
