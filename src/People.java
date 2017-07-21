
public class People {
	private int index;
	private int numOfPeople;
	private String name;
	private double[] moneyToReceive;

	public People(int index, int numOfPeople, String name){
		this.setIndex(index);
		this.setNumOfPeople(numOfPeople);
		this.setName(name);
		setMoneyToReceive(new double[numOfPeople]);
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getNumOfPeople() {
		return numOfPeople;
	}


	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double[] getMoneyToReceive() {
		return moneyToReceive;
	}


	public void setMoneyToReceive(double[] moneyToReceive) {
		this.moneyToReceive = moneyToReceive;
	}
	
	public double getBill(int index){
		return moneyToReceive[index];
	}
	
	public void setBill(int index, double amount){
		this.moneyToReceive[index] = amount;
	}
}
