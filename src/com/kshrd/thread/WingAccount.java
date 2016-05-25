package com.kshrd.thread;
/*
 * 
 * */
public class WingAccount {

	int balance = 10;

	synchronized void withdraw(int amount) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " is withdrawing...");
		if (balance < amount) {
			System.out.println(name + " - not enough balance, waiting to deposit...");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance -= amount;
		System.out.println(name + " withdraw successfully.");
		System.out.println("Balance : " + balance + "$");
	}

	synchronized void deposit(int amount) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " is depositing...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		balance += amount;
		System.out.println(name + " deposit successfully");
		// notify();
		notifyAll();
		System.out.println("Balance : " + balance + "$");
	}

	public static void main(String[] args) throws InterruptedException {

		final WingAccount card = new WingAccount();
		Thread p1 = new Thread("P1") {
			public void run() {
				card.withdraw(20);
			};
		};

		Thread p2 = new Thread("P2") {
			public void run() {
				card.withdraw(30);
			};
		};

		Thread p3 = new Thread("P3") {
			public void run() {
				card.deposit(100);
			};
		};
		p1.start();
		p2.start();
		p3.start();
	}
}
