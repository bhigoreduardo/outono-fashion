import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-commentary',
  templateUrl: './commentary.component.html',
  styleUrls: ['./commentary.component.scss']
})
export class CommentaryComponent implements OnInit {

  @Input() date!: string;
  @Input() author!: string;
  @Input() rate!: string;
  @Input() commentary!: string;
  stars: number[] = [];

  constructor() { }

  ngOnInit(): void {
    this.initializeVars();
  }

  initializeVars() {
    this.stars = new Array(Number(this.rate));
  }

}
