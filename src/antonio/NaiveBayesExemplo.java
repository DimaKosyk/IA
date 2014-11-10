package antonio;

public class NaiveBayesExemplo {
	
	/* Atributos */
	
	private enum Outlook {
		SUNNY, OVERCAST, RAIN;
	}

	private enum Temperature {
		HOT, MILD, COOL;
	}

	private enum Humidity {
		HIGH, NORMAL;
	}

	private enum Wind {
		STRONG, WEAK;
	}

	/* Classes */

	private enum PlayTennis {
		YES, NO;
	}

	public static final Object[] POSSIVEIS_CLASSES = { PlayTennis.YES, PlayTennis.NO };
	
	/* Conjunto de treinamento */
	
	private static final Instancia D1 = new Instancia(new Object[] {Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, Wind.WEAK}, PlayTennis.NO);
	private static final Instancia D2 = new Instancia(new Object[] {Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, Wind.STRONG}, PlayTennis.NO);
	private static final Instancia D3 = new Instancia(new Object[] {Outlook.OVERCAST, Temperature.HOT, Humidity.HIGH, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D4 = new Instancia(new Object[] {Outlook.RAIN, Temperature.MILD, Humidity.HIGH, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D5 = new Instancia(new Object[] {Outlook.RAIN, Temperature.COOL, Humidity.NORMAL, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D6 = new Instancia(new Object[] {Outlook.RAIN, Temperature.COOL, Humidity.NORMAL, Wind.STRONG}, PlayTennis.NO);
	private static final Instancia D7 = new Instancia(new Object[] {Outlook.OVERCAST, Temperature.COOL, Humidity.NORMAL, Wind.STRONG}, PlayTennis.YES);
	private static final Instancia D8 = new Instancia(new Object[] {Outlook.SUNNY, Temperature.MILD, Humidity.HIGH, Wind.WEAK}, PlayTennis.NO);
	private static final Instancia D9 = new Instancia(new Object[] {Outlook.SUNNY, Temperature.COOL, Humidity.NORMAL, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D10 = new Instancia(new Object[] {Outlook.RAIN, Temperature.MILD, Humidity.NORMAL, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D11 = new Instancia(new Object[] {Outlook.SUNNY, Temperature.MILD, Humidity.NORMAL, Wind.STRONG}, PlayTennis.YES);
	private static final Instancia D12 = new Instancia(new Object[] {Outlook.OVERCAST, Temperature.MILD, Humidity.HIGH, Wind.STRONG}, PlayTennis.YES);
	private static final Instancia D13 = new Instancia(new Object[] {Outlook.OVERCAST, Temperature.HOT, Humidity.NORMAL, Wind.WEAK}, PlayTennis.YES);
	private static final Instancia D14 = new Instancia(new Object[] {Outlook.RAIN, Temperature.MILD, Humidity.HIGH, Wind.STRONG}, PlayTennis.NO);
				
	public static final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[] {
			D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14 };
	
	/* Conjunto de teste */

	public static final Instancia X = new Instancia(new Object[] {
			Outlook.SUNNY, Temperature.COOL, Humidity.HIGH, Wind.STRONG }); // PlayTennis.NO

	/* Exemplo */

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes(CONJUNTO_DE_TREINAMENTO,
				POSSIVEIS_CLASSES);

		Object classe = nb.classificar(X);

		if (classe != null) {
			System.out.print("Classe escolhida (com maior probabilidade) = "
					+ classe);
		} else {
			System.out.println("Não foi possível classificar!");
		}
	}
	
}