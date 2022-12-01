import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  policyBoolean!: Boolean;

  setPolicy(value: any): void {
    this.policyBoolean = JSON.parse(value);
  }
}
