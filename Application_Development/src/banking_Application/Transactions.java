package banking_Application;

public class Transactions {

	private String transaction_type;
	private long amount;
	private long balance;

	public Transactions(String transaction_type, long amount, long balance) {
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.balance = balance;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public long getAmount() {
		return amount;
	}

	public long getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Transactions [ transaction_type=" + transaction_type + ", amount=" + amount + ", balance=" + balance
				+ "]";
	}
}
