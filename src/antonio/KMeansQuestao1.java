package antonio;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import org.math.plot.Plot2DPanel;

public class KMeansQuestao1 {
	
	/* Parâmetros */
	
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
	
	private static final Color COR_CENTROIDES = Color.RED;
	private static final Color[] CORES = new Color[] {Color.GREEN, Color.BLUE};
	
	/* Questão */
	
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