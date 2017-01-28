package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.ShoppingCartDto;

public class GetShoppingCartResult implements Result {
    private ShoppingCartDto shoppingCardDto;

    public GetShoppingCartResult(ShoppingCartDto shoppingCardDto) {
	super();
	this.shoppingCardDto = shoppingCardDto;
    }

    public GetShoppingCartResult() {
    }

    public ShoppingCartDto getShoppingCardDto() {
	return shoppingCardDto;
    }

    public void setShoppingCardDto(ShoppingCartDto shoppingCardDto) {
	this.shoppingCardDto = shoppingCardDto;
    }

}
