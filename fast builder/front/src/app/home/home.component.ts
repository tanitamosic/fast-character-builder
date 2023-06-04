import { Component, OnInit } from '@angular/core';
import {BackgroundService} from "../services/background.service";
import {ClassService} from "../services/class.service";
import {RaceService} from "../services/race.service";

interface Size {
  name: string;
  size: string;
}

interface WayOfLife {
  name: string;
  origin: string;
}

interface Interest {
  name: string;
  interest: string;
}

interface PathInLife {
  name: string;
  path: string;
}

interface Amount {
  name: string,
  amount: string;
}

interface Disposition {
  name: string;
  disposition: string;
}

interface ColorPalette {
  name: string;
  color: string;
}

interface Skintone {
  name: string;
  color: string;
}

interface Feature {
  name: string;
  feature: string;
}

interface Build {
  name: string;
  build: string;
}

interface Skill {
  name: string;
  skill: string;
}

interface PartyLevel {
  name: string;
  level: number;
}

interface Class {
  name: string;
  charClass: string;
}

interface SubClass {
  name: string;
  charClass: string;
  subClass: string;
}

class Teammate {
  charClass: Class;
  subClass: SubClass;
  skills: Skill[] = [];

  constructor(obj: any) {
    this.charClass = obj.charClass;
    this.subClass = obj.subClass;
    this.skills = obj.skills;
  }

  getDto() {
    return {
      charClass: this.charClass.charClass,
      subClass: this.subClass.subClass,
      skills: this.skills
    }
  }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // GLOBALS
  creationStarted = false;

  // RACE
  sizes: Size[] = [{name: 'Short', size: 'SHORT'}, {name: 'Medium', size: 'MEDIUM'}, {name: 'Tall', size: 'TALL'}];
  dispositions: Disposition[] = [];
  colorPalettes: ColorPalette[] = [];
  features: Feature[] = [];
  skinTones: Skintone[] = [];
  builds: Build[] = [{name: 'Lean', build: 'LEAN'}, {name: 'Stocky', build: 'STOCKY'}, {name: 'Slight', build: 'SLIGHT'}, {name: 'Broad', build: 'BROAD'}];
  size: any;
  disposition: any;
  palette: any;
  selectedFeatures: any;
  skintone: any;
  build: any;

  // BACKGROUND
  waysOfLife: WayOfLife[] = [{name: 'Wandering spirit', origin: 'WANDERING_SPIRIT'}, {name: 'Loner', origin: 'LONER'}, {name: 'One place', origin: 'ONE_PLACE'}, {name: 'Organization member', origin: 'ORGANIZATION_MEMBER'}];
  wayOfLife: any;
  interests: Interest[] = [];
  pathsInLife: PathInLife[] = [];
  interest: any;
  path: any;

  // CLASS
  magicAmounts: Amount[] = [{name: 'Not at all', amount: 'None'}, {name: 'A little', amount: 'Low'}, {name: 'A decent amount', amount: 'Mid'}, {name: 'High magic', amount: 'High'}, {name: 'Spellcasters only', amount: 'Full'}]
  technologyAmounts: Amount[] = [{name: 'Not at all', amount: 'None'}, {name: 'A little', amount: 'Low'}, {name: 'A decent amount', amount: 'Mid'}, {name: 'Modern', amount: 'High'}, {name: 'Futuristic', amount: 'Full'}]
  darkAmounts: Amount[] = [{name: 'Not at all', amount: 'None'}, {name: 'A little', amount: 'Low'}, {name: 'A decent amount', amount: 'Mid'}, {name: 'Very', amount: 'High'}, {name: 'We are the bad guys', amount: 'Full'}]
  partyLevels: PartyLevel[] = [{name: '1', level: 1}, {name: '2', level: 2}, {name: '3', level: 3}];
  magicAmount: any;
  technologyAmount: any;
  darkAmount: any;
  level: any;

  teammates: Teammate[] = []

  teamClasses: Class[] = [
    {charClass: 'ARTIFICER', name: "Artificer"},
    {charClass: 'BARBARIAN', name: 'Barbarian'},
    {charClass: 'BARD', name: 'Bard'},
    {charClass: 'CLERIC', name: 'Cleric'},
    {charClass: 'DRUID', name: 'Druid'},
    {charClass: 'FIGHTER', name: 'Fighter'},
    {charClass: 'MONK', name: 'Monk'},
    {charClass: 'PALADIN', name: 'Paladin'},
    {charClass: 'RANGER', name: 'Ranger'},
    {charClass: 'ROGUE', name: 'Rogue'},
    {charClass: 'WARLOCK', name: 'Warlock'},
    {charClass: 'SORCERER', name: 'Sorcerer'},
    {charClass: 'WIZARD', name: 'Wizard'}
  ]
  teamSubclasses: SubClass[] = [
    {subClass: 'ALCHEMIST', charClass: 'ARTIFICER', name: "Alchemist"},
    {subClass: 'ARTILLERIST', charClass: 'ARTIFICER', name: "Artillerist"},
    {subClass: 'BEAST', charClass: 'BARBARIAN', name: "Path of the Beast"},
    {subClass: 'BERSERKER', charClass: 'BARBARIAN', name: "Path of the Berserker"},
    {subClass: 'TOTEM_WARRIOR', charClass: 'BARBARIAN', name: "Path of the Totem Warrior"},
    {subClass: 'ZEALOT', charClass: 'BARBARIAN', name: "Path of the Beast"},
    {subClass: 'LORE', charClass: 'BARD', name: "College of Lore"},
    {subClass: 'VALOR', charClass: 'BARD', name: "College of Valor"},
    {subClass: 'GLAMOUR', charClass: 'BARD', name: "College of Glamour"},
    {subClass: 'LIFE', charClass: 'CLERIC', name: "Life Domain"},
    {subClass: 'TEMPEST', charClass: 'CLERIC', name: "Tempest Domain"},
    {subClass: 'WAR', charClass: 'CLERIC', name: "War Domain"},
    {subClass: 'KNOWLEDGE', charClass: 'CLERIC', name: "Knowledge Domain"},
    {subClass: 'MOON', charClass: 'DRUID', name: "Circle of the Moon"},
    {subClass: 'LAND', charClass: 'DRUID', name: "Circle of the Land"},
    {subClass: 'DREAMS', charClass: 'DRUID', name: "Circle of Dreams"},
    {subClass: 'BATTLE_MASTER', charClass: 'FIGHTER', name: "Battle Master"},
    {subClass: 'CHAMPION', charClass: 'FIGHTER', name: "Champion"},
    {subClass: 'ELDRICH_KNIGHT', charClass: 'FIGHTER', name: "Eldrich Knight"},
    {subClass: 'DRUNKEN_MASTER', charClass: 'MONK', name: "Way of the Drunken Master"},
    {subClass: 'KENSEI', charClass: 'MONK', name: "Way of the Kensei"},
    {subClass: 'OPEN_HAND', charClass: 'MONK', name: "Way of the Open Hand"},
    {subClass: 'ANCIENTS', charClass: 'PALADIN', name: "Oath of the Ancients"},
    {subClass: 'DEVOTION', charClass: 'PALADIN', name: "Oath of the Devotion"},
    {subClass: 'HUNTER', charClass: 'RANGER', name: "Hunter"},
    {subClass: 'GLOOM', charClass: 'RANGER', name: "Gloom Stalker"},
    {subClass: 'HORIZON', charClass: 'RANGER', name: "Horizon Walker"},
    {subClass: 'ASSASSIN', charClass: 'ROGUE', name: "Assassin"},
    {subClass: 'ARCANE_TRICKSTER', charClass: 'ROGUE', name: "Arcane Trickster"},
    {subClass: 'SWASHBUCKLER', charClass: 'ROGUE', name: "Swashbuckler"},
    {subClass: 'THIEF', charClass: 'ROGUE', name: "Thief"},
    {subClass: 'DRACONIC', charClass: 'SORCERER', name: "Draconic"},
    {subClass: 'DIVINE_SOUL', charClass: 'SORCERER', name: "Divine Soul"},
    {subClass: 'SHADOW', charClass: 'SORCERER', name: "Shadow Magic"},
    {subClass: 'WILD', charClass: 'SORCERER', name: "Wild Magic"},
    {subClass: 'ARCHFEY', charClass: 'WARLOCK', name: "Archfey Patron"},
    {subClass: 'FIEND', charClass: 'WARLOCK', name: "Fiend Patron"},
    {subClass: 'GREAT_OLD_ONE', charClass: 'WARLOCK', name: "Great Old One Patron"},
    {subClass: 'ABJURATION', charClass: 'WIZARD', name: "School of Abjuration"},
    {subClass: 'CONJURATION', charClass: 'WIZARD', name: "School of Conjuration"},
    {subClass: 'DIVINATION', charClass: 'WIZARD', name: "School of Divination"},
    {subClass: 'EVOCATION', charClass: 'WIZARD', name: "School of Evocation"},
    {subClass: 'NECROMANCY', charClass: 'WIZARD', name: "School of Necromancy"},
    {subClass: 'WAR_MAGIC', charClass: 'WIZARD', name: "War Magic"}
  ]
  teamSkills: Skill[] = [
    {skill: 'ACROBATICS', name: 'Acrobatics'},
    {skill: 'ANIMAL_HANDLING', name: 'Animal handling'},
    {skill: 'ARCANA', name: 'Arcana'},
    {skill: 'ATHLETICS', name: 'Athletics'},
    {skill: 'DECEPTION', name: 'Deception'},
    {skill: 'HISTORY', name: 'History'},
    {skill: 'INSIGHT', name: 'Insight'},
    {skill: 'INTIMIDATION', name: 'Intimidation'},
    {skill: 'INVESTIGATION', name: 'Investigation'},
    {skill: 'MEDICINE', name: 'Medicine'},
    {skill: 'NATURE', name: 'Nature'},
    {skill: 'PERCEPTION', name: 'Perception'},
    {skill: 'PERFORMANCE', name: 'Performance'},
    {skill: 'PERSUASION', name: 'Persuasion'},
    {skill: 'RELIGION', name: 'Religion'},
    {skill: 'SLEIGH_OF_HAND', name: 'Sleigh of hand'},
    {skill: 'STEALTH', name: 'Stealth'},
    {skill: 'SURVIVAL', name: 'Survival'},
    {skill: 'THIEVES_TOOLS', name: 'Thieves tools'},
  ]

  // FLAGS
  wayOfLifeChosen: boolean = false;
  interestChosen: boolean = false;
  showDisposition: boolean = false;
  showPalette: boolean = false;
  showSkintone: boolean = false;
  showFeatures: boolean = false;
  showBuilds: boolean = false;
  displayCreationDoneModal: boolean = false;


  constructor(private backgroundService: BackgroundService, private classService: ClassService, private raceService: RaceService) { }

  ngOnInit(): void {
  }

  start() {
    this.creationStarted = true;
  }

  wayPicked() {
    // TODO: IMPLEMENT
    console.log(this.wayOfLife)
    this.wayOfLifeChosen = true;
    this.displayCreationDoneModal = true;
  }

  interestPicked() {
    // TODO: IMPLEMENT
    this.interestChosen = true;
  }

  pathPicked() {
    // TODO: IMPLEMENT
  }

  sizePicked() {
    // TODO: IMPLEMENT
  }

  dispositionPicked() {
    // TODO: IMPLEMENT
  }

  palettePicked() {
    // TODO: IMPLEMENT
  }

  addTeammate() {
    if (this.teammates.length === 10) return;
    this.teammates.push(new Teammate({charClass: undefined, subClass: undefined, skills: []})); // Add a new teammate object
  }

  removeTeammate() {
    this.teammates.pop(); // Remove the last teammate object
  }

  getSubclasses(charClass: Class) {
    let subclasses: SubClass[] = [];
    if (charClass === undefined) return subclasses;
    this.teamSubclasses.forEach(e => {
      if (e.charClass === charClass.charClass) {
        subclasses.push(e)
      }
    })
    return subclasses;
  }

  onClassChange(i: number) {
    let t = this.teammates.at(i);
    console.log(t);
  }

  skintonePicked() {
    // TODO: IMPLEMENT
  }

  buildPicked() {
    // TODO: IMPLEMENT
  }

  onComplete($event: boolean) {
    // TODO: IMPLEMENT
  }

  exportAsPDF() {
    // TODO: IMPLEMENT
  }
}
