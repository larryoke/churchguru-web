package com.laotek.churchguru.web.server.handler.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.laotek.churchguru.commons.ShoppingCart;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.api.GetShoppingCartAction;
import com.laotek.churchguru.web.client.activity.api.GetShoppingCartResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.ShoppingCartDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

//Commented out to enable spring-boot
//@Component
@Deprecated
public class CreateApiShoppingCartHandler extends AbstractCommandHandler
	implements ActionHandler<GetShoppingCartAction, GetShoppingCartResult> {

    @Autowired
    private RestTemplate apiRestTemplate;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${api.uri}")
    private String apiUri;

    @Override
    public GetShoppingCartResult execute(GetShoppingCartAction action, ExecutionContext context)
	    throws DispatchException {

	Map<String, Integer> var = new HashMap<String, Integer>();
	var.put("amountRequired", action.getAmountOfCredit());
	ShoppingCart shoppingCart = (ShoppingCart) apiRestTemplate
		.getForObject(apiUri + "/services/sms/shoppingcart/{amountRequired}", ShoppingCart.class, var);

	ShoppingCartDto dto = map(shoppingCart);
	return new GetShoppingCartResult(dto);
    }

    private ShoppingCartDto map(ShoppingCart shoppingCart) {
	ShoppingCartDto dto = new ShoppingCartDto();
	dto.setDesc(shoppingCart.getDesc());
	dto.setPrice(shoppingCart.getPrice());
	dto.setQuantity(shoppingCart.getQuantity());
	dto.setSubtotal(shoppingCart.getSubtotal());
	dto.setTotal(shoppingCart.getTotal());
	dto.setVatRate(shoppingCart.getVatRate());
	dto.setVatValue(shoppingCart.getVatValue());
	return dto;
    }

    @Override
    public Class<GetShoppingCartAction> getActionType() {
	return GetShoppingCartAction.class;
    }

    @Override
    public void rollback(GetShoppingCartAction action, GetShoppingCartResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
