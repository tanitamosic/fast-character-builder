import {Component, OnInit} from '@angular/core';
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



class CharSheet {
  background: string | undefined;
  race: string | undefined;
  charClass: string | undefined;
}

class BackgroundParams {
  wayOfLife: string | undefined;
  interest: string | undefined;
  path: string | undefined;
  integer: number | undefined;
}

class RaceParams {
  size: string | undefined;
  disposition: string | undefined;
  skintone: string | undefined;
  feature: string | undefined;
  pallette: string | undefined;
  build: string | undefined;
  strangeIndex: number | undefined;
}

interface BackgroundWrapper {
  charSheet: CharSheet;
  backgroundParams: BackgroundParams;
  nextOptions: [];
}

interface RaceWrapper {
  charSheet: CharSheet;
  raceParams: RaceParams;
  nextOptions: [];
  optionTypePalette: boolean;
  optionTypeDisposition: boolean;
  optionTypeSkin: boolean;
  optionTypeFeature: boolean;
  optionTypeBuild: boolean;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // GLOBALS
  creationStarted = true;
  charSheet: CharSheet = {background: undefined, charClass: undefined, race: undefined}

  backgroundParams: BackgroundParams = {wayOfLife: undefined, interest: undefined, path: undefined, integer: undefined}
  backgroundWrapper: BackgroundWrapper = {charSheet: this.charSheet, backgroundParams: this.backgroundParams, nextOptions: []}

  raceParams: RaceParams = {size: undefined, disposition: undefined, skintone: undefined, feature: undefined, pallette: undefined, build: undefined, strangeIndex: undefined}
  raceWrapper: RaceWrapper = {charSheet: this.charSheet, raceParams: this.raceParams, nextOptions: [], optionTypeDisposition: false, optionTypeFeature: false, optionTypePalette: false, optionTypeSkin: false, optionTypeBuild: false}

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
  selectedFeature: any;
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
  showInterest: boolean = false;
  showPath: boolean = false;
  showDisposition: boolean = false;
  showPalette: boolean = false;
  showSkintone: boolean = false;
  showFeatures: boolean = false;
  showBuilds: boolean = false;
  displayCreationDoneModal: boolean = false;
  sizeDisabled: boolean = false;
  dispositionDisabled: boolean = false;
  paletteDisabled: boolean = false;
  skinDisabled: boolean = false;
  featuresDisabled: boolean = false;
  buildDisabled: boolean = false;
  wayDisabled: boolean = false;
  interestDisabled: boolean = false;
  pathDisabled: boolean = false;



  constructor(private backgroundService: BackgroundService, private classService: ClassService, private raceService: RaceService) { }

  ngOnInit(): void {
  }

  start() {
    this.creationStarted = true;
  }

  wayPicked() {
    this.backgroundWrapper.nextOptions = []
    this.backgroundWrapper.backgroundParams.wayOfLife = this.wayOfLife.origin;
    const request = this.backgroundService.getNewOptions(this.backgroundWrapper);
    request.subscribe((res) => {
      console.log(res);
      const backgroundWrapper = res as BackgroundWrapper;
      backgroundWrapper.nextOptions.forEach((e) => {
        this.interests.push({name: this.capitalize(e), interest: e})
      });
      this.wayDisabled = true;
      this.backgroundWrapper = backgroundWrapper;
      this.showInterest = true;

    });
  }

  interestPicked() {
    this.backgroundWrapper.nextOptions = []
    this.backgroundWrapper.backgroundParams.interest = this.interest.interest;
    const request = this.backgroundService.getNewOptions(this.backgroundWrapper);
    request.subscribe((res) => {
      console.log(res);
      const backgroundWrapper = res as BackgroundWrapper;
      if (backgroundWrapper.charSheet.background !== undefined && backgroundWrapper.charSheet.background !== null) {
        this.charSheet.background = backgroundWrapper.charSheet.background;
        this.backgroundWrapper = backgroundWrapper;
        this.interestDisabled = true;
        return;
      }
      backgroundWrapper.nextOptions.forEach((e) => {
        this.pathsInLife.push({name: this.capitalize(e), path: e})
      });
      this.interestDisabled = true;
      this.backgroundWrapper = backgroundWrapper;
      this.showPath = true;

    });
  }

  pathPicked() {
    this.backgroundWrapper.nextOptions = []
    this.backgroundWrapper.backgroundParams.path = this.path.path;
    const request = this.backgroundService.getNewOptions(this.backgroundWrapper);
    request.subscribe((res) => {
      console.log(res);
      const backgroundWrapper = res as BackgroundWrapper;
      this.pathDisabled = true;
      this.backgroundWrapper = backgroundWrapper;
      this.charSheet.background = backgroundWrapper.charSheet.background;
    });
  }

  sizePicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.size = this.size.size;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      this.fillDropdown(raceWrapper);
      this.sizeDisabled = true;
      this.raceWrapper = raceWrapper;

    })
  }

  dispositionPicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.disposition = this.disposition.disposition;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      if (raceWrapper.charSheet.race !== undefined && raceWrapper.charSheet.race !== null) {
        this.charSheet.race = raceWrapper.charSheet.race;
      }
      this.fillDropdown(raceWrapper);
      this.dispositionDisabled = true;
      this.raceWrapper = raceWrapper;

    });
  }

  palettePicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.pallette = this.palette.color;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      if (raceWrapper.charSheet.race !== undefined && raceWrapper.charSheet.race !== null) {
        this.charSheet.race = raceWrapper.charSheet.race;
      }
      this.fillDropdown(raceWrapper);
      this.paletteDisabled = true;
      this.raceWrapper = raceWrapper;
    })
  }

  skintonePicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.skintone = this.skintone.color;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      if (raceWrapper.charSheet.race !== undefined && raceWrapper.charSheet.race !== null) {
        this.charSheet.race = raceWrapper.charSheet.race;
      }
      this.fillDropdown(raceWrapper);
      this.skinDisabled = true;
      this.raceWrapper = raceWrapper;
    })
  }

  buildPicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.build = this.build.build;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      if (raceWrapper.charSheet.race !== undefined && raceWrapper.charSheet.race !== null) {
        this.charSheet.race = raceWrapper.charSheet.race;
      }
      this.fillDropdown(raceWrapper);
      this.buildDisabled = true;
      this.raceWrapper = raceWrapper;
    })
  }

  featurePicked() {
    this.raceWrapper.nextOptions = []
    this.raceWrapper.raceParams.feature = this.selectedFeature.feature;
    const request = this.raceService.getNewOptions(this.raceWrapper);
    request.subscribe((res) => {
      console.log(res);
      const raceWrapper = res as RaceWrapper;
      if (raceWrapper.charSheet.race !== undefined && raceWrapper.charSheet.race !== null) {
        this.charSheet.race = raceWrapper.charSheet.race;
      }
      this.fillDropdown(raceWrapper);
      this.featuresDisabled = true;
      this.raceWrapper = raceWrapper;
    })
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

  onComplete($event: boolean) {
    // TODO: IMPLEMENT
  }

  exportAsPDF() {
    // TODO: IMPLEMENT
  }

  fillDropdown(raceWrapper: RaceWrapper) {
    // fills appropriate dropdowns, and sets flags.
    if (raceWrapper.optionTypePalette) {
      this.showPalette = true;
      raceWrapper.optionTypePalette = false;
      raceWrapper.nextOptions.forEach((e) => {
        this.colorPalettes.push({name: this.capitalize(e), color: e})
      });
    } else if (raceWrapper.optionTypeSkin) {
      this.showSkintone = true;
      raceWrapper.optionTypeSkin = false;
      raceWrapper.nextOptions.forEach((e) => {
        this.skinTones.push({name: this.capitalize(e), color: e})
      });
    } else if (raceWrapper.optionTypeDisposition) {
      this.showDisposition = true;
      raceWrapper.optionTypeDisposition = false;
      raceWrapper.nextOptions.forEach((e) => {
        this.dispositions.push({name: this.capitalize(e), disposition: e})
      });
    } else if (raceWrapper.optionTypeFeature) {
      this.showFeatures = true;
      raceWrapper.optionTypeFeature = false;
      raceWrapper.nextOptions.forEach((e) => {
        this.features.push({name: this.capitalize(e), feature: e})
      });
    } else if (raceWrapper.optionTypeBuild) {
      this.showBuilds = true;
      raceWrapper.optionTypeBuild = false;
      raceWrapper.nextOptions.forEach((e) => {
        this.builds.push({name: this.capitalize(e), build: e})
      });
    }
  }

  capitalize(str: string): string {
    str = str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
    return str.replace(/_/g, " ")
  }

}
