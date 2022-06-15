package banking_Application;

public class BankDB {
	
	private String name;
	private String encrypted_password;
	private long account_number;
	private long customer_id;
	private long balance;
	private String transaction_type;
	private long amount;
	
	public BankDB(Customer customer,Transactions transaction) {
		this.name=customer.getName();
		this.account_number=customer.getAccount_number();
		this.customer_id=customer.getCustomer_id();
		this.encrypted_password=customer.getEncrypted_password();
		this.amount=transaction.getAmount();
		this.balance=transaction.getBalance();
		this.transaction_type=transaction.getTransaction_type();
		
	}

	public String getName() {
		return name;
	}

	public String getEncrypted_password() {
		return encrypted_password;
	}

	public long getAccount_number() {
		return account_number;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public long getBalance() {
		return balance;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public long getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "BankDB [name=" + name + ", encrypted_password=" + encrypted_password + ", account_number="
				+ account_number + ", customer_id=" + customer_id + ", balance=" + balance + ", transaction_type="
				+ transaction_type + ", amount=" + amount + "]";
	}
	

}
