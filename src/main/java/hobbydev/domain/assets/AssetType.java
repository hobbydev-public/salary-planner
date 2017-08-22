package hobbydev.domain.assets;

public enum AssetType {
	CASH("Cash"),
	BANK_ACCOUNT("Bank account");
	
	private String label;
	
	private AssetType(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
