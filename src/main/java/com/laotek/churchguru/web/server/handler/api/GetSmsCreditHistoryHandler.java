package com.laotek.churchguru.web.server.handler.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.laotek.churchguru.commons.SmsCreditHistory;
import com.laotek.churchguru.commons.SmsCreditHistoryItem;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.client.activity.api.GetSmsCreditHistoryAction;
import com.laotek.churchguru.web.client.activity.api.GetSmsCreditHistoryResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.SmsCreditHistoryDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

//@Component
@Deprecated
public class GetSmsCreditHistoryHandler extends AbstractCommandHandler
	implements ActionHandler<GetSmsCreditHistoryAction, GetSmsCreditHistoryResult> {

    @Autowired
    private RestTemplate apiRestTemplate;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${api.uri}")
    private String apiUri;

    @Override
    public GetSmsCreditHistoryResult execute(GetSmsCreditHistoryAction action, ExecutionContext context)
	    throws DispatchException {
	try {
	    Organisation org = organisationDao.getOrganisationFromClientSessionId(action.getClientSessionId());

	    SmsCreditHistory smsCreditHistory = apiRestTemplate.getForObject(
		    apiUri + "/services/sms/secure/{subdomain}/smsCreditHistory", SmsCreditHistory.class,
		    org.getSubdomain());

	    GetSmsCreditHistoryResult result = new GetSmsCreditHistoryResult();
	    List<SmsCreditHistoryItem> smsCreditHistoryItems = smsCreditHistory.getItems();
	    result.setSmsCreditHistoryDto(map(smsCreditHistoryItems));
	    return result;

	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage() + " " + apiUri);
	}
    }

    private List<SmsCreditHistoryDto> map(List<SmsCreditHistoryItem> smsCreditHistoryItems) {
	List<SmsCreditHistoryDto> smsCreditHistoryDtos = new ArrayList<SmsCreditHistoryDto>();
	for (SmsCreditHistoryItem item : smsCreditHistoryItems) {
	    SmsCreditHistoryDto dto = new SmsCreditHistoryDto();
	    dto.setDate(map(item.getCreatedDate()));
	    dto.setCreditBalance(item.getCurrentCreditBalance());
	    dto.setEventCredit(item.getCurrentEventCredit());
	    dto.setCredit(item.getSmsCreditHistoryStatus().isCredit());
	    dto.setDesc(item.getSmsCreditHistoryStatus().getDesc());
	    dto.setDesc(item.getSmsCreditHistoryStatus().getDesc());
	    smsCreditHistoryDtos.add(dto);
	}
	return smsCreditHistoryDtos;
    }

    private String map(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	return sdf.format(date);
    }

    @Override
    public Class<GetSmsCreditHistoryAction> getActionType() {
	return GetSmsCreditHistoryAction.class;
    }

    @Override
    public void rollback(GetSmsCreditHistoryAction arg0, GetSmsCreditHistoryResult arg1, ExecutionContext arg2)
	    throws DispatchException {
	// TODO Auto-generated method stub

    }

}
