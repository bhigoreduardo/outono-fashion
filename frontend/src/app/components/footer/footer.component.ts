import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  logo: string = '/assets/images/logo.svg';

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
