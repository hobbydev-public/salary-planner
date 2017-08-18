package hobbydev.api.models.be.generic;

import hobbydev.domain.core.IdentifiedEntityInterface;

public abstract class AbstractModel {
	
	protected Long id;
	
	protected AbstractModel(){}
	
	public AbstractModel(IdentifiedEntityInterface domain) {
		setId(domain.getId());
	}
	
	public abstract Long getId();
	public abstract void setId(Long id);
}
