package hobbydev.domain.assets;

import hobbydev.domain.core.LabeledEnum;

public enum AssetType implements LabeledEnum<AssetType> {
	CASH("Cash"),
	BANK_ACCOUNT("Bank account");
	
	private String label;
	
	private AssetType(String label){
		this.label = label;
	}
	
	@Override
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
