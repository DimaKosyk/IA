package antonio;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import org.math.plot.Plot2DPanel;

public class KMeansQuestao2 {
	
	/* Parâmetros */
	
	// Dados: a1=(2,10); a2=(2,5); a3=(8,4); a4=(5,8); a5=(7,5); a6=(6,4); a7=(1,2); a8=(4,9);
	// centróides iniciais: c1=a1; c2=a4; c3=a7;
	
	private static final Instancia A1 = new Instancia(new Object[] {2d, 10d}, null);
	private static final Instancia A2 = new Instancia(new Object[] {2d, 5d}, null);
	private static final Instancia A3 = new Instancia(new Object[] {8d, 4d}, null);
	private static final Instancia A4 = new Instancia(new Object[] {5d, 8d}, null);
	private static final Instancia A5 = new Instancia(new Object[] {7d, 5d}, null);
	private static final Instancia A6 = new Instancia(new Object[] {6d, 4d}, null);
	private static final Instancia A7 = new Instancia(new Object[] {1d, 2d}, null);
	private static final Instancia A8 = new Instancia(new Object[] {4d, 9d}, null);
	
	private static final Instancia C1 = A1;
	private static final Instancia C2 = A4;
	private static final Instancia C3 = A7;
				
	private static final Instancia[] CONJUNTO_DE_TREINAMENTO = new Instancia[] {A1, A2, A3, A4, A5, A6, A7, A8};
	
	private static final Instancia[] CENTROIDES_INICIAIS = new Instancia[] {C1, C2, C3};
	
	private static final int K = 3;
	
	private static final Color COR_CENTROIDES = Color.RED;
	private static final Color[] CORES = new Color[] {Color.GREEN, Color.BLUE, Color.YELLOW};
	
	/* Resolução */
	
	private static KMeans kmeans;
	
	private static JFrame janela;
	
	private static Plot2DPanel planoCartesiano;

	private static void melhorarAgrupamento() {
		if (kmeans.melhorarAgrupamento()) {
			System.out.println("Nova iteração!");
			System.out.println();
			plotar();
		} else {
			System.out.println("Não há como melhorar esse agrupamento!");
			System.out.println();
			JOptionPane.showMessageDialog(janela, "Não há como melhorar esse agrupamento!");
		}
	}
	
	private static void plotar() {
		planoCartesiano.removeAllPlots();
		HashMap<Instancia, Set<Instancia>> grupos = kmeans.getGrupos();
		Object[] centroides = grupos.keySet().toArray();
		double[] centroidesX = new double[K];
		double[] centroidesY = new double[K];
		for (int g = 0; g < K; g++) {
			System.out.println("Grupo " + (g + 1));
			Instancia centroide = (Instancia) centroides[g];
			centroidesX[g] = (Double) centroide.getAtributos()[0];
			centroidesY[g] = (Double) centroide.getAtributos()[1];
			System.out.println("Centróide: " + centroide);
			Object[] grupo = grupos.get(centroide).toArray();
			double[] instanciasX = new double[grupo.length];
			double[] instanciasY = new double[grupo.length];
			System.out.println("Instâncias:");
			for (int i = 0; i < grupo.length; i++) {
				Instancia instancia = (Instancia) grupo[i];
				System.out.println(instancia);
				instanciasX[i] = (Double) instancia.getAtributos()[0];
				instanciasY[i] = (Double) instancia.getAtributos()[1];
			}
			planoCartesiano.addScatterPlot("Grupo " + (g + 1), CORES[g], instanciasX, instanciasY);
			System.out.println();
		}
		planoCartesiano.addScatterPlot("Centróides", COR_CENTROIDES, centroidesX, centroidesY);
	}
	
	public static void main(String[] args) {
		kmeans = new KMeans(CONJUNTO_DE_TREINAMENTO, K, CENTROIDES_INICIAIS);
		
		janela = new JFrame("K-means");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
		
		planoCartesiano = new Plot2DPanel();
		janela.add(planoCartesiano, BorderLayout.CENTER);
		
		plotar();
		
		JButton botaoMelhorarAgrupamento = new JButton("Melhorar agrupamento");
		botaoMelhorarAgrupamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melhorarAgrupamento();
			}
		});
		janela.add(botaoMelhorarAgrupamento, BorderLayout.SOUTH);
		
		janela.setSize(500, 500);
		//janela.pack();
		janela.setVisible(true);
	}
	
}