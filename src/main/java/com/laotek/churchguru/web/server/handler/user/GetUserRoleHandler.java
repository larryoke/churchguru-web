package com.laotek.churchguru.web.server.handler.user;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.UserRole;
import com.laotek.churchguru.web.client.activity.user.GetUserRoleAction;
import com.laotek.churchguru.web.client.activity.user.GetUserRoleResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserRoleDto;

@Component
public class GetUserRoleHandler extends AbstractCommandHandler
implements ActionHandler<GetUserRoleAction, GetUserRoleResult> {

	@Autowired
	private UserRoleDao userRoleService;
	
	@Override
	public GetUserRoleResult execute(GetUserRoleAction action,
			ExecutionContext context) throws DispatchException {
		
		List<UserRole> adminRoles = userRoleService.getUserRoles();
		
		GetUserRoleResult result = new GetUserRoleResult();
		result.setDtos(convertRoles(adminRoles));
		return result;
	}

	@Override
	public Class<GetUserRoleAction> getActionType() {
		return GetUserRoleAction.class;
	}

	@Override
	public void rollback(GetUserRoleAction action, GetUserRoleResult result,
			ExecutionContext context) throws DispatchException {		
	}
	
	private UserRoleDto convert(UserRole level){
		UserRoleDto dto = new UserRoleDto();
		dto.setId(level.getId());
		dto.setAdminRoleName(level.getUserRoleName());
		return dto;
	}
	
	private List<UserRoleDto> convertRoles(List<UserRole> roles){
		List<UserRoleDto> dtos = new ArrayList<UserRoleDto>();
		for(UserRole role:roles){
			UserRoleDto dto = convert(role);
			dtos.add(dto);
		}
		return dtos;
	}

}
