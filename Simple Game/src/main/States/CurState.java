package main.States;

public class CurState {
	private StateID curId;
	
	public CurState(StateID curId) {
		this.curId = curId;
	}
	
	public void setId(StateID curId) {
		this.curId = curId;
	}
	
	public StateID getId() {
		return curId;
	}
}
