import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'compare'
})
export class ComparePipe implements PipeTransform {

  transform(arr: string[], value: string): string {
    value = value.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');

    if (arr.includes(value)) {
      return value;
    };

    return '';
  }

}
