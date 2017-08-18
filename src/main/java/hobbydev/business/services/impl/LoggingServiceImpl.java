package hobbydev.business.services.impl;

import hobbydev.business.AbstractService;
import hobbydev.business.services.LoggingService;
import hobbydev.domain.core.LogEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoggingServiceImpl extends AbstractService implements LoggingService {
	
	@Override
	protected Class<LogEntry> getEntityClass() {
		return LogEntry.class;
	}
	
	@Override
	@Transactional
	public LogEntry addLog(LogEntry logEntry) {
		getDAO().create(logEntry);
		return logEntry;
	}
	
	@Override
	@Transactional
	public List<LogEntry> listLogs() {
		return getDAO().getAll(getEntityClass());
	}
}
