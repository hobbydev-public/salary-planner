package hobbydev.domain.core;

public interface LabeledEnum<E extends Enum<E>> {
	
	String getLabel();
}
