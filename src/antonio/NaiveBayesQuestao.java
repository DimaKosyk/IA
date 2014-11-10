package antonio;

public class NaiveBayesQuestao {
	
	/* Atributos */
	
	private enum GiveBirth {
		YES, NO;
	}

	private enum CanFly {
		YES, NO;
	}

	private enum LiveInWater {
		YES, NO, SOMETIMES;
	}

	private enum HaveLegs {
		YES, NO;
	}

	/* Classes */

	private enum Class {
		REPTILES, FISHES, MAMMALS, AMPHIBIANS, BIRDS;
	}

	public static final Object[] POSSIVEIS_CLASSES = { Class.REPTILES,
			Class.FISHES, Class.MAMMALS, Class.AMPHIBIANS, Class.BIRDS };
	
	/* Conjunto de treinamento */
	
	private static final Instancia PYTHON = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.NO, HaveLegs.NO}, Class.REPTILES);
	private static final Instancia SALMON = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.YES, HaveLegs.NO}, Class.FISHES);
	private static final Instancia WHALE = new Instancia(new Object[] {GiveBirth.YES, CanFly.NO, LiveInWater.YES, HaveLegs.NO}, Class.MAMMALS);
	private static final Instancia FROG = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.SOMETIMES, HaveLegs.YES}, Class.AMPHIBIANS);
	private static final Instancia KOMODO = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.NO, HaveLegs.YES}, Class.REPTILES);
	private static final Instancia BAT = new Instancia(new Object[] {GiveBirth.YES, CanFly.YES, LiveInWater.NO, HaveLegs.YES}, Class.MAMMALS);
	private static final Instancia PIGEON = new Instancia(new Object[] {GiveBirth.NO, CanFly.YES, LiveInWater.NO, HaveLegs.YES}, Class.BIRDS);
	private static final Instancia CAT = new Instancia(new Object[] {GiveBirth.YES, CanFly.NO, LiveInWater.NO, HaveLegs.YES}, Class.MAMMALS);
	private static final Instancia LEOPARD_SHARK = new Instancia(new Object[] {GiveBirth.YES, CanFly.NO, LiveInWater.YES, HaveLegs.NO}, Class.FISHES);
	private static final Instancia TURTLE = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.SOMETIMES, HaveLegs.YES}, Class.REPTILES);
	private static final Instancia PENGUIN = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.SOMETIMES, HaveLegs.YES}, Class.BIRDS);
	private static final Instancia PORCUPINE = new Instancia(new Object[] {GiveBirth.YES, CanFly.NO, LiveInWater.NO, HaveLegs.YES}, Class.MAMMALS);
	private static final Instancia EEL = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.YES, HaveLegs.NO}, Class.FISHES);
	private static final Instancia SALAMANDER = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.SOMETIMES, HaveLegs.YES}, Class.AMPHIBIANS);
	private static final Instancia GILA_MONSTER = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.NO, HaveLegs.YES}, Class.REPTILES);
	private static final Instancia PLATYPUS = new Instancia(new Object[] {GiveBirth.NO, CanFly.NO, LiveInWater.NO, HaveLegs.YES}, Class.MAMMALS);
	private static final Instancia OWL = new Instancia(new Object[] {GiveBirth.NO, CanFly.YES, LiveInWater.NO, HaveLegs.YES}, Class.BIRDS);
	private static final Instancia DOLPHIN = new Instancia(new Object[] {GiveBirth.YES, CanFly.NO, LiveInWater.YES, HaveLegs.NO}, Class.MAMMALS);
	private static final Instancia EAGLE = new Instancia(new Object[] {GiveBirth.NO, CanFly.YES, LiveInWater.NO, HaveLegs.YES}, Class.BIRDS);
				
	public static final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[] {
			PYTHON, SALMON, WHALE, FROG, KOMODO, BAT, PIGEON, CAT,
			LEOPARD_SHARK, TURTLE, PENGUIN, PORCUPINE, EEL, SALAMANDER,
			GILA_MONSTER, PLATYPUS, OWL, DOLPHIN, EAGLE };
	
	/* Conjunto de teste */

	public static final Instancia HUMAN = new Instancia(new Object[] {
			GiveBirth.YES, CanFly.NO, LiveInWater.NO, HaveLegs.YES }); // Class.MAMMALS

	/* Questão */
	
	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes(CONJUNTO_DE_TREINAMENTO,
				POSSIVEIS_CLASSES);

		Object classe = nb.classificar(HUMAN);

		if (classe != null) {
			System.out.print("Classe escolhida (com maior probabilidade) = "
					+ classe);
		} else {
			System.out.println("Não foi possível classificar!");
		}
	}
	
}