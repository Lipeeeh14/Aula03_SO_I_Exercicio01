package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {}

	private String os() {	
		return System.getProperty("os.name");
	}
	
	private void executaComandoIp(String process) {
		// PS: Acho que poderia ser desenvolvido de uma maneira
		// melhor usando o split, mas consegui fazer dessa maneira
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			String adaptador;
			try {
				do {
					if (linha.contains("Adaptador")) { 
						adaptador = linha;
						linha = buffer.readLine();						
						do {
							if (linha.contains("IPv4")) { 
								System.out.println(adaptador);
								System.out.println(linha);
							}
							
							linha = buffer.readLine();
						} while(!linha.contains("Adaptador"));
					} else {	
						linha = buffer.readLine();
					}
				} while(linha != null);
			} catch(NullPointerException e1) {
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ip() {
		String os = os();
		
		if (os.contains("Windows")) {
			executaComandoIp("ipconfig");
		} else {
			executaComandoIp("ifconfig");
		}
	}
	
	public void ping() {
		String os = os();
		
		if (os.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("PING -4 -n 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if (linha.contains("Aproximar")) {
						linha = buffer.readLine();
						String partesLinha[] = linha.split(",");
						String media[];
						int posicao = partesLinha.length - 1;
						
						media = partesLinha[posicao].split(" ");
						System.out.println(media[3]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Process p = Runtime.getRuntime().exec("PING -4 -c 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if (linha.contains("rtt")) {
						String partesLinha[] = linha.split(" ");
						String media[];
						
						media = partesLinha[3].split("/");
						System.out.println(media[1]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
