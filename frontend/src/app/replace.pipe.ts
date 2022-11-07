import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'replace'
})
export class ReplacePipe implements PipeTransform {

  transform(value: string, regexValue?: string, replaceValue?: string): any {
    if (regexValue != null && replaceValue != null) {
      let regex = new RegExp(regexValue, 'g');
      value = value.replace(regex, replaceValue);
    }

    return value.toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  }

}
