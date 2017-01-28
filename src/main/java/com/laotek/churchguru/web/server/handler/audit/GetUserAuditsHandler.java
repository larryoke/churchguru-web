package com.laotek.churchguru.web.server.handler.audit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.model.UserAudit;
import com.laotek.churchguru.web.client.activity.user.GetUserAuditsAction;
import com.laotek.churchguru.web.client.activity.user.GetUserAuditsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserAuditDto;

@Component
public class GetUserAuditsHandler extends AbstractCommandHandler
implements ActionHandler<GetUserAuditsAction, GetUserAuditsResult> {
	
	private static Log logger = LogFactory.getLog(GetUserAuditsHandler.class);

	@Autowired
	private UserAuditDao userAuditService;
	
	@Override
	public GetUserAuditsResult execute(GetUserAuditsAction action,
			ExecutionContext context) throws DispatchException {
		logger.info("->");
		List<UserAudit> audits = userAuditService.getUserAuditLogs(action.getUserId(), action.getBeginIndex(), action.getEndIndex());
		List<UserAuditDto> dtos = map(audits);
		return new GetUserAuditsResult(dtos);
	}

	@Override
	public Class<GetUserAuditsAction> getActionType() {
		return GetUserAuditsAction.class;
	}

	@Override
	public void rollback(GetUserAuditsAction action, GetUserAuditsResult result,
			ExecutionContext context) throws DispatchException {}

	private List<UserAuditDto> map(List<UserAudit> audits){
		List<UserAuditDto> dtos = new ArrayList<UserAuditDto>();
		for(UserAudit audit: audits){
			UserAuditDto dto = new UserAuditDto();
			dto.setDateAndTime(format(audit.getCreatedDate()));
			dto.setDetails(audit.getMessage());
			dto.setUserAuditTypeName(audit.getUserLogType().getUserAuditTypeName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	private String format(Date dateAndTime){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		return sdf.format(dateAndTime);
	}
}
