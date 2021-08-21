package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rdsController = new RedesController();
		int opcao;
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja fazer ?\n"
					+ "1 - Verificar o IP\n"
					+ "2 - Verificar a m�dia do ping\n"
					+ "3 - Finalizar"));
			
			switch(opcao) {
				case 1:
					rdsController.ip();
					break;
				case 2:
					rdsController.ping();
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "At� a pr�xima!!!");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Op��o inv�lida, digite novamente!");
			}
		} while(opcao != 3);
	}
}
