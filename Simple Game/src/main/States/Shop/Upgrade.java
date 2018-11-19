package main.States.Shop;

public class Upgrade{
	
	private double value, inc;
	private int cost, costIncrease;
	private String upgradeName, sign;

	
	public Upgrade(String upgradeName, String sign, double value, double inc, int cost, int costIncrease) {
		this.sign = sign;
		this.value = value;
		this.inc = inc;
		this.cost = cost;
		this.costIncrease = costIncrease;
		this.upgradeName = upgradeName;
	}
	
	
	public void buy() {
		cost += costIncrease;
		value += inc;
	}
	
	
	
	//Getter Setter
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getInc() {
		return inc;
	}
	public void setInc(double inc) {
		this.inc = inc;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCostIncrease() {
		return costIncrease;
	}
	public void setCostIncrease(int costIncrease) {
		this.costIncrease = costIncrease;
	}
	public String getUpgradeName() {
		return upgradeName;
	}
	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
