export class CharSheet {
  background: string | undefined;
  race: string | undefined;
  charClass: string | undefined;
  subclass: string | undefined;
  proficiencies: string[] | undefined;
  abilityScores: { [key: string]: number } | undefined;
  classString: string | undefined;

  constructor() {
    this.background = undefined;
    this.race = undefined;
    this.charClass = undefined;
    this.subclass = undefined;
    this.proficiencies = undefined;
    this.abilityScores = undefined;
    this.classString = undefined;
  }

  getBackground() {
    return this.background === undefined ? '' : this.background
  }
  getRace() {
    return this.race === undefined ? '' : this.race
  }
  getCharClass() {
    return this.charClass === undefined ? '' : this.charClass
  }
  getSubClass() {
    return this.subclass === undefined ? '' : this.subclass
  }
  getClassString() {
    return this.classString === undefined ? '' : this.classString
  }
  getProficiencies() {
    return this.proficiencies === undefined ? [] : this.proficiencies
  }

  isReady(): boolean {
    return this.abilityScores !== undefined && this.race !== undefined && this.charClass !== undefined &&
      this.subclass !== undefined && this.proficiencies !== undefined
  }


}
