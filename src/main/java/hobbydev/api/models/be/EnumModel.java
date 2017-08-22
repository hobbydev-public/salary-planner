package hobbydev.api.models.be;

import hobbydev.domain.core.LabeledEnum;

public class EnumModel {
	
	private String code;
	private String label;
	
	public EnumModel(LabeledEnum domain) {
		this.code = domain.toString();
		this.label = domain.getLabel();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
