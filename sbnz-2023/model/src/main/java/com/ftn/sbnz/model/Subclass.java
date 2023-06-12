package com.ftn.sbnz.model;

public enum Subclass{

    ALCHEMIST(CharClass.ARTIFICER,"Alchemist"),
    ARTILLERIST(CharClass.ARTIFICER,"Artillerist"),

    BEAST(CharClass.BARBARIAN, "Path of the Beast"),
    BERSERKER(CharClass.BARBARIAN, "Path of the Berserker"),
    TOTEM_WARRIOR(CharClass.BARBARIAN, "Path of the Totem Warrior"),
    ZEALOT(CharClass.BARBARIAN, "Path of the Beast"),

    LORE(CharClass.BARD, "College of Lore"),
    VALOR(CharClass.BARD, "College of Valor"),
    GLAMOUR(CharClass.BARD, "College of Glamour"),

    LIFE(CharClass.CLERIC, "Life Domain"),
    TEMPEST(CharClass.CLERIC, "Tempest Domain"),
    WAR(CharClass.CLERIC, "War Domain"),
    KNOWLEDGE(CharClass.CLERIC, "Knowledge Domain"),

    MOON(CharClass.DRUID, "Circle of the Moon"),
    LAND(CharClass.DRUID, "Circle of the Land"),
    DREAMS(CharClass.DRUID, "Circle of Dreams"),


    BATTLE_MASTER(CharClass.FIGHTER, "Battle Master"),
    CHAMPION(CharClass.FIGHTER, "Champion"),
    ELDRICH_KNIGHT(CharClass.FIGHTER, "Eldrich Knight"),

    DRUNKEN_MASTER(CharClass.MONK, "Way of the Drunken Master"),
    KENSEI(CharClass.MONK, "Way of the Kensei"),
    OPEN_HAND(CharClass.MONK, "Way of the Open Hand"),

    ANCIENTS(CharClass.PALADIN, "Oath of the Ancients"),
    DEVOTION(CharClass.PALADIN, "Oath of the Devotion"),

    HUNTER(CharClass.RANGER, "Hunter"),
    GLOOM(CharClass.RANGER, "Gloom Stalker"),
    HORIZON(CharClass.RANGER, "Horizon Walker"),

    ASSASSIN(CharClass.ROGUE, "Assassin"),
    ARCANE_TRICKSTER(CharClass.ROGUE, "Arcane Trickster"),
    SWASHBUCKLER(CharClass.ROGUE, "Swashbuckler"),
    THIEF(CharClass.ROGUE, "Thief"),

    DRACONIC(CharClass.SORCERER, "Draconic"),
    DIVINE_SOUL(CharClass.SORCERER, "Divine Soul"),
    SHADOW(CharClass.SORCERER, "Shadow Magic"),
    WILD(CharClass.SORCERER, "Wild Magic"),

    ARCHFEY(CharClass.WARLOCK, "Archfey Patron"),
    FIEND(CharClass.WARLOCK, "Fiend Patron"),
    GREAT_OLD_ONE(CharClass.WARLOCK, "Great Old One Patron"),

    ABJURATION(CharClass.WIZARD, "School of Abjuration"),
    CONJURATION(CharClass.WIZARD, "School of Conjuration"),
    DIVINATION(CharClass.WIZARD, "School of Divination"),
    EVOCATION(CharClass.WIZARD, "School of Evocation"),
    NECROMANCY(CharClass.WIZARD, "School of Necromancy"),
    WAR_MAGIC(CharClass.WIZARD, "War Magic"),
    NO_SUBCLASS(null,"No Subclass")
    ;

    private final String name;
    private final CharClass charClass;

    private Subclass(CharClass charclass, String name) {
        this.charClass = charclass;
        this.name = name;
    }
    public CharClass getCharClass() {return charClass;}

    public String getDisplayName() {
        return name + " " + charClass.getDisplayName();
    }
    public String getEnumString() {return "Subclass." + this.toString();}
}
