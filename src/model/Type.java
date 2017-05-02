package model;

public enum Type {
	Normal, Fire, Fighting, Water, Flying, Grass, Poison, Electric, Ground, Psychic, Rock, Ice, Bug, Dragon, Ghost, Dark, Steel, Fairy, Questionable;
	public Type[] weak;
	public Type[] strong;
	public Type[] noEffect;

	static {
		Normal.weak = new Type[] { Fighting, Steel };
		Normal.strong = new Type[] {};
		Normal.noEffect = new Type[] {  };

		Fire.weak = new Type[] { Water, Rock, Ground };
		Fire.strong = new Type[] { Grass, Ice, Bug, Steel };
		Fire.noEffect = new Type[] {};

		Fighting.weak = new Type[] {Bug, Rock, Dark};
		Fighting.strong = new Type[] { Normal, Ice, Rock, Dark, Steel };
		Fighting.noEffect = new Type[] { };

		Water.weak = new Type[] {Grass, Electric};
		Water.strong = new Type[] {Fire, Ground, Rock};
		Water.noEffect = new Type[] {};

		Flying.weak = new Type[] {Electric, Rock, Ice};
		Flying.strong = new Type[] {Grass, Fighting, Bug};
		Flying.noEffect = new Type[] {Ground};

		Grass.weak = new Type[] {Fire, Poison, Ice, Flying, Bug};
		Grass.strong = new Type[] {Water, Ground, Rock};
		Grass.noEffect = new Type[] {};

		Poison.weak = new Type[] {Ground, Psychic};
		Poison.strong = new Type[] {Grass, Fairy};
		Poison.noEffect = new Type[] {};

		Electric.weak = new Type[] {Grass, Dragon};
		Electric.strong = new Type[] {Water, Flying};
		Electric.noEffect = new Type[] {};

		Ground.weak = new Type[] {Ground};
		Ground.strong = new Type[] {Fire, Electric, Poison, Rock, Steel};
		Ground.noEffect = new Type[] {Electric};

		Psychic.weak = new Type[] {Dark, Bug, Ghost};
		Psychic.strong = new Type[] {Fighting, Poison};
		Psychic.noEffect = new Type[] {};

		Rock.weak = new Type[] {Water, Grass, Fighting, Ground, Steel};
		Rock.strong = new Type[] {Fire, Ice, Flying, Bug};
		Rock.noEffect = new Type[] {};

		Ice.weak = new Type[] {Fire, Fighting, Rock, Steel};
		Ice.strong = new Type[] {Grass, Ground, Flying, Dragon};
		Ice.noEffect = new Type[] {};

		Bug.weak = new Type[] {Fire, Flying, Steel};
		Bug.strong = new Type[] {Grass, Psychic, Dark};
		Bug.noEffect = new Type[] {};

		Dragon.weak = new Type[] {Dragon, Ice, Fairy};
		Dragon.strong = new Type[] {Dragon};
		Dragon.noEffect = new Type[] {};

		Ghost.weak = new Type[] {Dark, Ghost};
		Ghost.strong = new Type[] {Psychic, Ghost};
		Ghost.noEffect = new Type[] {Normal, Fighting};

		Dark.weak = new Type[] {Fighting, Bug, Fairy};
		Dark.strong = new Type[] {Psychic, Ghost};
		Dark.noEffect = new Type[] {Psychic};

		Steel.weak = new Type[] {Fire,Fighting, Ground};
		Steel.strong = new Type[] {Ice, Rock, Fairy};
		Steel.noEffect = new Type[] {Poison};

		Fairy.weak = new Type[] {Fire, Poison, Steel};
		Fairy.strong = new Type[] {Fighting, Dragon, Dark};
		Fairy.noEffect = new Type[] {Dragon};

	}
}
