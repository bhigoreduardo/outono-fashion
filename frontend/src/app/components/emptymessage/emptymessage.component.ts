import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-emptymessage',
  templateUrl: './emptymessage.component.html',
  styleUrls: ['./emptymessage.component.scss']
})
export class EmptymessageComponent implements OnInit {

  @Input() message!: string;
  @Input() routerLink!: string;
  @Input() icon!: string;
  @Input() link!: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  redirectTo(): void {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([this.routerLink]);
    });
  }

}
