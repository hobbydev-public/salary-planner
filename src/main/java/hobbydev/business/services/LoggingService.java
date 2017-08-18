package hobbydev.business.services;

import hobbydev.domain.core.LogEntry;

import java.util.List;

public interface LoggingService {
	
	List<LogEntry> listLogs();
	LogEntry addLog(LogEntry logEntry);
}
