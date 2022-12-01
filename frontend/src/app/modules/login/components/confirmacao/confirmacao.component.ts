import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmacao',
  templateUrl: './confirmacao.component.html',
  styleUrls: ['./confirmacao.component.scss']
})
export class ConfirmacaoComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';
  backgroundImage: string = '/assets/images/login.png';

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  redirectTo(link: string): void {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([link]);
    });
  }

}
