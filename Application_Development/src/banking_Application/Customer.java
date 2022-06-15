package banking_Application;

public class Customer {
	
	private String name;
	private String encrypted_password;
	private long account_number;
	private long customer_id;
	private long balance;
	
	public Customer(String name,String encrypted_password,long account_number,long customer_id,long balance) {
		this.name=name;
		this.account_number=account_number;
		this.encrypted_password=encrypted_password;
		this.customer_id=customer_id;
		this.balance=balance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEncrypted_password(String encrypted_password) {
		this.encrypted_password = encrypted_password;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public void setBalance(long balance) {
		this.balance = balance;
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

	@Override
	public String toString() {
		return "Customer [name=" + name + ", encrypted_password=" + encrypted_password + ", account_number="
				+ account_number + ", customer_id=" + customer_id + ", balance=" + balance + "]";
	}
	
	
	

}
