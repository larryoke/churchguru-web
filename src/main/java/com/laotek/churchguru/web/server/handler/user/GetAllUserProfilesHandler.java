package com.laotek.churchguru.web.server.handler.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.web.client.activity.user.GetAllUserProfilesAction;
import com.laotek.churchguru.web.client.activity.user.GetAllUserProfilesResult;
import com.laotek.churchguru.web.server.UserRoleHelper;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.UserProfileDto;

@Component
public class GetAllUserProfilesHandler extends AbstractCommandHandler implements
	ActionHandler<GetAllUserProfilesAction, GetAllUserProfilesResult> {

    @Autowired
    private UserRoleDao userRoleService;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetAllUserProfilesResult execute(GetAllUserProfilesAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation organisation = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	List<UserProfile> profiles = userRoleService
		.getUserProfiles(organisation);
	List<UserProfileDto> profileDtos = new ArrayList<UserProfileDto>();
	for (UserProfile profile : profiles) {
	    UserProfileDto dto = new UserProfileDto();
	    dto.setUserProfileName(profile.getName());
	    dto.setUserProfileIdentifier(profile.getIdentifier());
	    UserRoleHelper.populateDto(profile, dto);

	    for (User user : profile.getUsers()) {
		UserDto userDto = new UserDto();
		userDto.setForenames(user.getForenames());
		userDto.setSurname(user.getSurname());
		dto.getUserDtos().add(userDto);
	    }

	    dto.setCreatedDate(getFormattedDate(profile.getCreatedDate()));
	    dto.setUpdatedDate(getFormattedDate(profile.getLastUpdatedDate()));

	    profileDtos.add(dto);
	}
	return new GetAllUserProfilesResult(profileDtos);
    }

    @Override
    public Class<GetAllUserProfilesAction> getActionType() {
	return GetAllUserProfilesAction.class;
    }

    @Override
    public void rollback(GetAllUserProfilesAction action,
	    GetAllUserProfilesResult result, ExecutionContext context)
	    throws DispatchException {
    }

    private String getFormattedDate(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	return sdf.format(date);
    }

}
