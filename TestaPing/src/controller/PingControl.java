package controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class PingControl {

	public String getAdaptador() {
		String os = System.getProperty("os.name");
		String ipv4 = "";

		if ("Windows".contains(os)) {
			try {
				Process proc = Runtime.getRuntime().exec("ipconfig /all ");
				InputStream fluxo = proc.getInputStream();
				InputStreamReader leFluxo = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leFluxo);
				String linha = buffer.readLine();

				while (linha != null) {
					if (linha.contains("Adaptador Ethernet")) {
						ipv4 += linha;
					} else if (linha.contains("IPv4" + " ")) {
						ipv4 += (linha.split(":")[1] + "\n");
					}
					linha = buffer.readLine();
				}
				return ipv4;
			} catch (IOException e) {
				e.getMessage();
			}
		} else if ("Linux".contains(os)) {

			return null;

		}
		return null;
	}

	public void getPing(String url) {
		String os = System.getProperty("os.name");
		if ("Windows".contains(os)) {
			String media = "";
			try {
				Process proc = Runtime.getRuntime().exec("ping " + url + " -n 10 ");
				InputStream fluxo = proc.getInputStream();
				InputStreamReader leFluxo = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leFluxo);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("dia"))
						media = linha.substring(42);
					linha = buffer.readLine();
				}

			} catch (IOException e) {
				e.getMessage();
			}
			JOptionPane.showMessageDialog(null, "Ping médio para o host: " + media);
		}
	}
}
