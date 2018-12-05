package com.laotek.churchguru.web.clientm.activity.privacypolicy;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class PrivacyPolicyViewImpl extends DetailViewGwtImpl implements PrivacyPolicyView {
	private Presenter presenter;

	private FlowPanel formContainer = new FlowPanel();
	private WidgetList widget = new WidgetList();
	private SimplePanel body = new SimplePanel();

	public PrivacyPolicyViewImpl() {
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
		scrollPanel.setWidget(formContainer);
		addLocation(formContainer);

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(
				new HTML("<p> Trinity Chapel built the Trinity Chapel app as a Free app. This SERVICE is provided by\n"
						+ "                    Trinity Chapel at no cost and is intended for use as is.\n"
						+ "                  </p> <p>This section is used to inform visitors regarding our policies with the collection, use, and disclosure\n"
						+ "                    of Personal Information if anyone decided to use our Service.\n"
						+ "                  </p> <p>If you choose to use our Service, then you agree to the collection and use of information in\n"
						+ "                    relation to this policy. The Personal Information that we collect is used for providing and improving\n"
						+ "                    the Service. We will not use or share your information with anyone except as described\n"
						+ "                    in this Privacy Policy.\n" + "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p>The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is\n"
						+ "                    accessible at Trinity Chapel unless otherwise defined in this Privacy Policy.\n"
						+ "                  </p> "));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Information Collection and Use</strong></p> <p>For a better experience, while using our Service, we may require you to provide us with certain\n"
						+ "                    personally identifiable information, including but not limited to Donation, email address, full name, membership status, Gift Aid status and full address. The information that we request will be retained by us and used as described in this privacy policy.\n"
						+ "                  </p> <p>The app does use third party services that may collect information used to identify you.</p> <div><p>Link to privacy policy of third party service providers used by the app</p> <ul><li><a href=\"https://www.google.com/policies/privacy/\" target=\"_blank\">Google Play Services</a></li><!----><!----><!----><!----><!----><!----><!----></ul></div> "));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Log Data</strong></p> <p> We want to inform you that whenever you use our Service, in a case of\n"
						+ "                    an error in the app we collect data and information (through third party products) on your phone\n"
						+ "                    called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address,\n"
						+ "                    device name, operating system version, the configuration of the app when utilizing our Service,\n"
						+ "                    the time and date of your use of the Service, and other statistics.\n"
						+ "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Cookies</strong></p> <p>Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers.\n"
						+ "                    These are sent to your browser from the websites that you visit and are stored on your device's internal\n"
						+ "                    memory.\n"
						+ "                  </p> <p>This Service does not use these “cookies” explicitly. However, the app may use third party code and\n"
						+ "                    libraries that use “cookies” to collect information and improve their services. You have the option to\n"
						+ "                    either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose\n"
						+ "                    to refuse our cookies, you may not be able to use some portions of this Service.\n"
						+ "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Service Providers</strong></p> <p> We may employ third-party companies and individuals due to the following reasons:</p> <ul><li>To facilitate our Service;</li> <li>To provide the Service on our behalf;</li> <li>To perform Service-related services; or</li> <li>To assist us in analyzing how our Service is used.</li></ul> <p> We want to inform users of this Service that these third parties have access to\n"
						+ "                    your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However,\n"
						+ "                    they are obligated not to disclose or use the information for any other purpose.\n"
						+ "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Security</strong></p> <p> We value your trust in providing us your Personal Information, thus we are striving\n"
						+ "                    to use commercially acceptable means of protecting it. But remember that no method of transmission over\n"
						+ "                    the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee\n"
						+ "                    its absolute security.\n" + "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Links to Other Sites</strong></p> <p>This Service may contain links to other sites. If you click on a third-party link, you will be directed\n"
						+ "                    to that site. Note that these external sites are not operated by us. Therefore, we strongly\n"
						+ "                    advise you to review the Privacy Policy of these websites. We have no control over\n"
						+ "                    and assume no responsibility for the content, privacy policies, or practices of any third-party sites\n"
						+ "                    or services.\n" + "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Children’s Privacy</strong></p> <p>These Services do not address anyone under the age of 13. We do not knowingly collect\n"
						+ "                    personally identifiable information from children under 13. In the case we discover that a child\n"
						+ "                    under 13 has provided us with personal information, we immediately delete this from\n"
						+ "                    our servers. If you are a parent or guardian and you are aware that your child has provided us with personal\n"
						+ "                    information, please contact us so that we will be able to do necessary actions.\n"
						+ "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Changes to This Privacy Policy</strong></p> <p> We may update our Privacy Policy from time to time. Thus, you are advised to review\n"
						+ "                    this page periodically for any changes. We will notify you of any changes by posting\n"
						+ "                    the new Privacy Policy on this page. These changes are effective immediately after they are posted on\n"
						+ "                    this page.\n" + "                  </p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(
				"<p><strong>Contact Us</strong></p> <p>If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact\n"
						+ "                    us.\n"
						+ "                  </p> <p>Please go to the About Trinity Chapel section to find our contact details.</p>"));

		formContainer.add(new HTML("&nbsp;"));

		formContainer.add(new HTML(""));

	}

	private void addLocation(FlowPanel container) {
		widget.setHeader(new HeaderLabel("Privacy Policy"));
		widget.add(body);
		container.add(widget);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showForm() {
	}

}
