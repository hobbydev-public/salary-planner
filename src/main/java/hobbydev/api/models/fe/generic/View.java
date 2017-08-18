package hobbydev.api.models.fe.generic;

import hobbydev.domain.core.IdentifiedEntityInterface;

public interface View<ENTITY extends IdentifiedEntityInterface> {
    
    ENTITY toDomain();
}
