import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {

  // Template Values
  @Input() image: string = '';
  @Input() name: string = '';
  @Input() priceMin: string = '';
  @Input() priceMax: string = '';
  @Input() offer: string = '';

  // Queries Values
  @Input() nome: string = '';
  @Input() id: string = '';

  constructor(
    private router: Router
  ) { }

  goToProduto(nome: string, id: string) {

    console.log(nome + id);

    // this.router.navigate(['produtos', nome, id]);

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['produtos', nome, id]);
    });

    // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['produtos', nome, id], { queryParams: query });
    // });
  }

}
