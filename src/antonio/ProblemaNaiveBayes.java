package antonio;

public class ProblemaNaiveBayes {

	public static final int GIVE_BIRTH_YES = 1;
	public static final int GIVE_BIRTH_NO = 2;
	
	public static final int CAN_FLY_YES = 1;
	public static final int CAN_FLY_NO = 2;
	
	public static final int LIVE_IN_WATER_YES = 1;
	public static final int LIVE_IN_WATER_NO = 2;
	public static final int LIVE_IN_WATER_SOMETIMES = 3;
	
	public static final int HAVE_LEGS_YES = 1;
	public static final int HAVE_LEGS_NO = 2;
	
	public static final int CLASS_REPTILES = 1;
	public static final int CLASS_FISHES = 2;
	public static final int CLASS_MAMMALS = 3;
	public static final int CLASS_AMPHIBIANS = 4;
	public static final int CLASS_BIRDS = 5;
	
	public static final int ATRIBUTE_GIVE_BIRTH = 0;
	public static final int ATRIBUTE_CAN_FLY = 1;
	public static final int ATRIBUTE_LIVE_IN_WATER = 2;
	public static final int ATRIBUTE_HAVE_LEGS = 3;
	public static final int ATRIBUTE_CLASS = 4;
	
	public static final int[] POSSIVEIS_CLASSES = {CLASS_REPTILES, CLASS_FISHES, CLASS_MAMMALS, CLASS_AMPHIBIANS, CLASS_BIRDS};
	
	public static final int[][] TRAINING_SET = new int[][] {
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_NO, CLASS_REPTILES},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_YES, HAVE_LEGS_NO, CLASS_FISHES},
		{GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_YES, HAVE_LEGS_NO, CLASS_MAMMALS},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_SOMETIMES, HAVE_LEGS_YES, CLASS_AMPHIBIANS},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_REPTILES},
		{GIVE_BIRTH_YES, CAN_FLY_YES, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_MAMMALS},
		{GIVE_BIRTH_NO, CAN_FLY_YES, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_BIRDS},
		{GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_MAMMALS},
		{GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_YES, HAVE_LEGS_NO, CLASS_FISHES},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_SOMETIMES, HAVE_LEGS_YES, CLASS_REPTILES},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_SOMETIMES, HAVE_LEGS_YES, CLASS_BIRDS},
		{GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_MAMMALS},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_YES, HAVE_LEGS_NO, CLASS_FISHES},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_SOMETIMES, HAVE_LEGS_YES, CLASS_AMPHIBIANS},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_REPTILES},
		{GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_MAMMALS},
		{GIVE_BIRTH_NO, CAN_FLY_YES, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_BIRDS},
		{GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_YES, HAVE_LEGS_NO, CLASS_MAMMALS},
		{GIVE_BIRTH_NO, CAN_FLY_YES, LIVE_IN_WATER_NO, HAVE_LEGS_YES, CLASS_BIRDS}
	};
	
	public static final int[] PYTHON = new int[] {GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_NO}; // CLASS_REPTILES
	public static final int[] PENGUIN = new int[] {GIVE_BIRTH_NO, CAN_FLY_NO, LIVE_IN_WATER_SOMETIMES, HAVE_LEGS_YES}; // CLASS_BIRDS
	public static final int[] HUMAN = new int[] {GIVE_BIRTH_YES, CAN_FLY_NO, LIVE_IN_WATER_NO, HAVE_LEGS_YES}; // CLASS_MAMMALS
	
	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes(TRAINING_SET);
		
		int classe = nb.classificar(HUMAN, POSSIVEIS_CLASSES);
		
		System.out.print("Classe escolhida (com maior probabilidade) = " + classe + " - ");
		switch (classe) {
		case CLASS_REPTILES:
			System.out.println("CLASS_REPTILES");
			break;
		case CLASS_FISHES:
			System.out.println("CLASS_FISHES");
			break;
		case CLASS_MAMMALS:
			System.out.println("CLASS_MAMMALS");
			break;
		case CLASS_AMPHIBIANS:
			System.out.println("CLASS_AMPHIBIANS");
			break;
		case CLASS_BIRDS:
			System.out.println("CLASS_BIRDS");
			break;
		default:
			System.out.println("Não foi possível classificar!");
			break;
		}
	}
	
}