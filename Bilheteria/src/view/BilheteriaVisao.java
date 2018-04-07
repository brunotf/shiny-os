package view;

import java.util.concurrent.Semaphore;
import controller.BilheteriaControl;

public class BilheteriaVisao {

	public static void main(String[] args) {
		int permissoes = 300;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int i = 0; i < 300; i++){
			
			Thread t = new BilheteriaControl(i, semaforo);
			t.start();
		}
	}
}