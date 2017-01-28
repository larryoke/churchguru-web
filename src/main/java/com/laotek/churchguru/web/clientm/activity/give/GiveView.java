package com.laotek.churchguru.web.clientm.activity.give;

import java.math.BigDecimal;
import java.util.Map;

import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface GiveView extends DetailView {

    void setPresenter(Presenter presenter);

    void pleaseWaitLoadingPaypal();

    void showForm();

    void showThanks();

    void showErrorMessage(String message);

    void goTo(String approvalUrl);

    public interface Presenter {
	void submit(Map<String, String> giveDetails,
		Map<DonationType, BigDecimal> payments);
    }
}
