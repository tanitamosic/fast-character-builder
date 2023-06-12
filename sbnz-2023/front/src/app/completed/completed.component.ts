import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit {

  @Output() creationCompleted = new EventEmitter<boolean>();
  @Output() modalClosed = new EventEmitter<boolean>();
  proficiencies = ['Acrobatics', 'Performance Deception', 'Persuasion', 'Sleigh of Hand']
  characterImage = 'assets/tiefling_lavander.png' //https://primefaces.org/cdn/primeng/images/usercard.png
  constructor() { }

  ngOnInit(): void {
  }

}
