package controller;

import java.util.concurrent.Semaphore;

public class BilheteriaControl extends Thread {
	private static int INGRESSO = 100;
	private int idPessoa;
	private Semaphore Semaforo;
	
	public BilheteriaControl(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.Semaforo = semaforo;
	}
	
	@Override
	public void run(){
		try {
			Semaforo.acquire();
			login();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			Semaforo.release();
		}
		
	}
	
	private void login(){
		int tempo = (int) ((Math.random() * 2000) + 50);
		
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(tempo > 1000)
			System.out.println("Tempo limite excedido.");
		else {
			System.out.println("Usuário #" + idPessoa + " criou login.");
			compra();
		}
	}
	
	private void compra () {
		int tempo = (int) ((Math.random() * 3000) + 1000);
		
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(tempo > 2500) 
			System.out.println("Tempo limite excedido.");
		else {
			int compra = (int) ((Math.random() * 4) + 1);
			if (INGRESSO - compra >= 0) {
				INGRESSO -= compra;
				System.out.println("Usuário #" +idPessoa +" comprou " + compra +" ingressos.");
				System.out.println("Ingressos disponiveis: " + INGRESSO);
			}
			else {
				if(INGRESSO == 0)
					System.out.println("Ingressos esgotados.");
				else			
				System.out.println("Ingressos insuficientes para compra.");
			}
		}	
	}
}
	

