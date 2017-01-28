package com.laotek.churchguru.web.clientm.activity.give;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

public class GiveDto {
    private static GiveDto instance = new GiveDto();
    private Map<String, String> giveDetails = new HashMap<String, String>();
    private Map<DonationType, BigDecimal> donateDetails = new HashMap<DonationType, BigDecimal>();

    public static GiveDto getInstance() {
	return instance;
    }

    public Map<String, String> getGiveDetails() {
	return giveDetails;
    }

    public Map<DonationType, BigDecimal> getDonateDetails() {
	return donateDetails;
    }
}
