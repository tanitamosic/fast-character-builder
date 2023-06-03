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

interface Skill {
  name: string;
  skill: string;
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
  charClass: string;
  subClass: string;
  skills: Skill[] = [];

  constructor(obj: any) {
    this.charClass = obj.charClass;
    this.subClass = obj.subClass;
    this.skills = obj.skills;
  }
  isReady() {
    return this.charClass !== '' && this.subClass !== '' && this.skills.length > 0
  }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // GLOBALS
  creationStarted = true;

  // RACE
  size: any;
  sizes: Size[] = [{name: 'Short', size: 'SHORT'}, {name: 'Medium', size: 'MEDIUM'}, {name: 'Tall', size: 'TALL'}];
  dispositions: Disposition[] = [];
  colorPalettes: ColorPalette[] = [];
  palette: any;
  disposition: any;

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
  magicAmount: any;
  technologyAmount: any;
  darkAmount: any;

  teamMemberCounter = 0;
  teammate1: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate2: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate3: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate4: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate5: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate6: Teammate = new Teammate({charClass: '', subclass: '', skills: []});
  teammate1class: any;
  teammate1subClass: any;
  teammate2class: any;
  teammate2subClass: any;
  teammate3class: any;
  teammate3subClass: any;
  teammate4class: any;
  teammate4subClass: any;
  teammate5class: any;
  teammate5subClass: any;
  teammate6class: any;
  teammate6subClass: any;

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
  isClass1Selected: boolean = false;
  isClass2Selected: boolean = false;
  isClass3Selected: boolean = false;
  isClass4Selected: boolean = false;
  isClass5Selected: boolean = false;
  isClass6Selected: boolean = false;


  constructor(private backgroundService: BackgroundService, private classService: ClassService, private raceService: RaceService) { }

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
    if (this.teamMemberCounter === 6) return;
    this.teamMemberCounter += 1;
  }

  removeTeammate() {
    switch (this.teamMemberCounter) {
      case 1: this.teammate1 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      case 2: this.teammate2 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      case 3: this.teammate3 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      case 4: this.teammate4 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      case 5: this.teammate5 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      case 6: this.teammate6 = new Teammate({charClass: '', subclass: '', skills: []}); break;
      default: break;
    }
    if (this.teamMemberCounter === 0) return;
    this.teamMemberCounter -= 1;
  }
}
