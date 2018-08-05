import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  accordionCustomCss = 'home-accordion-group';
  showContent = '';
  isMenuOpen = false;
  isContactOpen = false;
  isNotifyOpen = false;

  constructor() {
  }

  ngOnInit() {
  }

  accordionChanged(index: number) {
    if (index === 0) {
      this.isMenuOpen = !this.isMenuOpen;
      this.isContactOpen = false;
      this.isNotifyOpen = false;
    } else if (index === 1) {
      this.isMenuOpen = false;
      this.isContactOpen = !this.isContactOpen;
      this.isNotifyOpen = false;
    } else if (index === 2) {
      this.isMenuOpen = false;
      this.isContactOpen = false;
      this.isNotifyOpen = !this.isNotifyOpen;
    }
  }

}
