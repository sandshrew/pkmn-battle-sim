package model;

public enum Type {
	Normal, Fire, Fighting, Water, Flying, Grass, Poison, Electric, Ground, Psychic, Rock, Ice, Bug, Dragon, Ghost, Dark, Steel, Fairy, Questionable;
	public Type[] weak;
	public Type[] strong;
	public Type[] noEffect;

	static {
		Normal.weak = new Type[] { Fighting, Steel };
		Normal.strong = new Type[] {};
		Normal.noEffect = new Type[] { Ghost };

		Fire.weak = new Type[] { Fire, Water, Rock, Dragon };
		Fire.strong = new Type[] { Grass, Ice, Bug, Steel };
		Fire.noEffect = new Type[] {};

		Fighting.weak = new Type[] { Poison, Flying, Psychic, Bug, Fairy };
		Fighting.strong = new Type[] { Normal, Ice, Rock, Dark, Steel };
		Fighting.noEffect = new Type[] { Ghost };

		Water.weak = new Type[] {Water, Grass, Dragon};
		Water.strong = new Type[] {Fire, Ground, Rock};
		Water.noEffect = new Type[] {};

		Flying.weak = new Type[] {Electric, Rock, Steel};
		Flying.strong = new Type[] {Grass, Fighting, Bug};
		Flying.noEffect = new Type[] {};

		Grass.weak = new Type[] {Fire, Grass, Poison, Flying, Bug, Dragon, Steel};
		Grass.strong = new Type[] {Water, Ground, Rock};
		Grass.noEffect = new Type[] {};

		Poison.weak = new Type[] {Poison, Ground, Rock, Ghost};
		Poison.strong = new Type[] {Grass, Fairy};
		Poison.noEffect = new Type[] {Steel};

		Electric.weak = new Type[] {Electric, Grass, Dragon};
		Electric.strong = new Type[] {Water, Flying};
		Electric.noEffect = new Type[] {Ground};

		Ground.weak = new Type[] {Grass, Bug};
		Ground.strong = new Type[] {Fire, Electric, Poison, Rock, Steel};
		Ground.noEffect = new Type[] {Flying};

		Psychic.weak = new Type[] {Psychic};
		Psychic.strong = new Type[] {Fighting, Poison};
		Psychic.noEffect = new Type[] {Dark};

		Rock.weak = new Type[] {Fighting, Ground, Steel};
		Rock.strong = new Type[] {Fire, Ice, Flying, Bug};
		Rock.noEffect = new Type[] {};

		Ice.weak = new Type[] {Fire, Water, Ice, Steel};
		Ice.strong = new Type[] {Grass, Ground, Flying, Dragon};
		Ice.noEffect = new Type[] {};

		Bug.weak = new Type[] {Fire, Fighting, Poison, Flying, Ghost, Steel, Fairy};
		Bug.strong = new Type[] {Grass, Psychic, Dark};
		Bug.noEffect = new Type[] {};

		Dragon.weak = new Type[] {Steel};
		Dragon.strong = new Type[] {Dragon};
		Dragon.noEffect = new Type[] {Fairy};

		Ghost.weak = new Type[] {Dark};
		Ghost.strong = new Type[] {Psychic, Ghost};
		Ghost.noEffect = new Type[] {Normal};

		Dark.weak = new Type[] {Fighting, Dark, Fairy};
		Dark.strong = new Type[] {Psychic, Ghost};
		Dark.noEffect = new Type[] {};

		Steel.weak = new Type[] {Fire, Water, Electric, Steel};
		Steel.strong = new Type[] {Ice, Rock, Fairy};
		Steel.noEffect = new Type[] {};

		Fairy.weak = new Type[] {Fire, Poison, Steel};
		Fairy.strong = new Type[] {Fighting, Dragon, Dark};
		Fairy.noEffect = new Type[] {};

	}
}
