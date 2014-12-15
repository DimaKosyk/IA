package antonio;

import java.util.*;

public class KMeansExemplo {
	
	/* Conjunto de treinamento */
	
	private static final Instancia P1 = new Instancia(new Object[] {1d, 0d}, null);
	private static final Instancia P2 = new Instancia(new Object[] {2d, 1d}, null);
	private static final Instancia P3 = new Instancia(new Object[] {0d, 0d}, null);
	private static final Instancia P4 = new Instancia(new Object[] {0d, 2d}, null);
	
//	private static final Instancia C1 = new Instancia(new Object[] {0d, -1d}, null);
//	private static final Instancia C2 = new Instancia(new Object[] {2d, 2d}, null);
	private static final Instancia C1 = new Instancia(new Object[] {2d, 0d}, null);
	private static final Instancia C2 = new Instancia(new Object[] {-1d, 0d}, null);
				
	private static final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[] {P1, P2, P3, P4};
	
	private static final Instancia[] CENTROIDES_INICIAIS = new Instancia[] {C1, C2};
	
	private static final int K = 2;
	
	/* Exemplo */
	
	private static KMeans kmeans;

	private static void plotar() {
		// TODO Mostrar em um gráfico
		HashMap<Instancia, Set<Instancia>> grupos = kmeans.getGrupos();
		Object[] centroides = grupos.keySet().toArray();
		for (int g = 0; g < grupos.size(); g++) {
			Instancia centroide = (Instancia) centroides[g];
			System.out.println("Grupo " + g);
			System.out.println("Centróide: " + centroide);
			System.out.println("Instâncias:");
			Set<Instancia> grupo = grupos.get(centroide);
			for (Instancia instancia : grupo) {
				System.out.println(instancia);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		kmeans = new KMeans(CONJUNTO_DE_TREINAMENTO, K, CENTROIDES_INICIAIS);
		plotar();
		while (kmeans.melhorarAgrupamento()) {
			System.out.println("Nova iteração!");
			System.out.println();
			plotar();
		}
		System.out.println("Fim");
	}
	
}