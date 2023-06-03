import { Component, OnInit } from '@angular/core';

interface Size {
  size: string;
}

interface WayOfLife {
  origin: string;
}

interface Interest {
  interest: string;
}

interface PathInLife {
  path: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  creationStarted = false;
  race1: any;
  sizes: Size[] = [{size: 'Short'}, {size: 'Medium'}, {size: 'Tall'}];
  waysOfLife: WayOfLife[] = [{origin: 'Wandering spirit'}, {origin: 'Loner'}, {origin: 'One place'}, {origin: 'Organization member'}];
  wayOfLife: any;
  interests: Interest[] = [];
  pathsInLife: PathInLife[] = [];
  interest: any;
  path: any;

  wayOfLifeChosen: boolean = false;
  interestChosen: boolean = false;


  constructor() { }

  ngOnInit(): void {
  }

  start() {
    this.creationStarted = true;
  }

  wayPicked() {
    // TODO: IMPLEMENT
    this.wayOfLifeChosen = true;
  }

  interestPicked() {
    // TODO: IMPLEMENT
  }

  pathPicked() {
    // TODO: IMPLEMENT
  }
}
