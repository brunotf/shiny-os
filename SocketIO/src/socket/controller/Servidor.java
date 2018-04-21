package socket.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public Servidor() {
		try {
			System.out.println("Servidor iniciado");
			ServerSocket server = new ServerSocket(20000);
			System.out.println("Porta 20000 reservada, aguardando conexão");
			Socket cli = server.accept();
			Cliente c = new Cliente();
			Thread t1 = new Thread(c);
			t1.start();
			System.out.println("Cliente conectado na porta");

			OutputStream out = cli.getOutputStream();
			out.write("Ola bem-vindo ao servidor de batepapo cristao\n\n\n".getBytes());
			out.flush();

			InputStream in = cli.getInputStream();
			Reader reader = new InputStreamReader(in);
			Reader readerKbd = new InputStreamReader(System.in);
			int i;
			while (true) {
				if (reader.ready()) {
					i = reader.read();
					System.out.print((char) i);
				}

				if (readerKbd.ready()) {
					int tecla = readerKbd.read();
					out.write(tecla);
					out.flush();
				}

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
