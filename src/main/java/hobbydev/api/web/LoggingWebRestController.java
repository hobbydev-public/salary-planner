package hobbydev.api.web;

import hobbydev.api.models.be.LogEntryModel;
import hobbydev.api.models.fe.LogEntryView;
import hobbydev.business.services.LoggingService;
import hobbydev.config.CurrentUser;
import hobbydev.domain.core.LogEntry;
import hobbydev.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/web/logs")
public class LoggingWebRestController {
	
	@Autowired
	private LoggingService loggingService;
	
	@RequestMapping(path = "{level}", method = RequestMethod.POST)
	public ResponseEntity<LogEntryModel> addLog(@RequestBody LogEntryView view, @CurrentUser User auth) {
		LogEntry entry = view.toDomain();
		entry.setSessionUser(auth);
		LogEntryModel logEntryModel = new LogEntryModel(loggingService.addLog(entry));
		
		return new ResponseEntity<>(logEntryModel, HttpStatus.CREATED);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<LogEntryModel>> listLogs() {
		List<LogEntryModel> models = loggingService.listLogs().stream()
				.map(domain -> new LogEntryModel(domain))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(models, HttpStatus.OK);
	}
}
