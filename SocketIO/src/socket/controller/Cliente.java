package socket.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;

public class Cliente implements Runnable{

	Socket srv;

	@Override
	public void run() {
		try {
			System.out.println("Cliente iniciando");
			srv = new Socket("10.68.76.187", 20000);
			System.out.println("Cliente conectado no servidor");
			InputStream in = srv.getInputStream();
			OutputStream out = srv.getOutputStream();
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

		try {
			srv.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
