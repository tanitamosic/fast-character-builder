import {Component, Input, OnInit} from '@angular/core';
import {CharSheet} from "../model/CharSheet";

@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit {

  @Input() charSheet: CharSheet = new CharSheet();
  @Input() skinColor: string | undefined;
  // characterImage = 'assets/tiefling_lavander.png' //https://primefaces.org/cdn/primeng/images/usercard.png
  constructor() { }

  ngOnInit(): void {
  }

  capitalize(str: string | undefined): string {
    if (str === undefined) { return ''; }
    // Ostavlja samo prvo slovo kapitalno, i zamnenjuje '_' razmacima ' '
    str = str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
    return str.replace(/_/g, " ")
  }

  capitalizeAll(strList: string[]) {
    let retval = ''
    strList.forEach((s) => {
      retval += this.capitalize(s) + ', '
    })
    return retval.slice(0, retval.length - 2);
  }


  getCharacterImage() {
    let race = this.charSheet?.race?.toLowerCase();
    let skinColor = this.skinColor?.toLowerCase();

    return skinColor === undefined ? 'assets/' + race + '.png' : 'assets/' + race + '_' + skinColor + '.png'
  }

  getScore(ability: string) {
    if (this.charSheet === undefined) return;
    if (this.charSheet.abilityScores === undefined) return;
    return this.charSheet.abilityScores[ability]
  }

}
