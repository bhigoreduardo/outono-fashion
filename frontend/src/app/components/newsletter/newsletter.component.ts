import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-newsletter',
  templateUrl: './newsletter.component.html',
  styleUrls: ['./newsletter.component.scss']
})
export class NewsletterComponent implements OnInit {

  newsletter: string = '/assets/images/background-newsletter.png'

  constructor() { }

  ngOnInit(): void {
  }

}
