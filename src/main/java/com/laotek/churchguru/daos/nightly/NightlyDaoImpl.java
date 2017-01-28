package com.laotek.churchguru.daos.nightly;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.PasswordResetCache;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NightlyDaoImpl extends BaseSessionFactory implements NightlyDao {

    @Override
    public void cleanUpPasswordResetCache() {
	Query query = getCurrentSession().createQuery(
		"from PasswordResetCache prc");
	@SuppressWarnings("unchecked")
	List<PasswordResetCache> passwordResetCaches = query.list();
	for (PasswordResetCache passwordResetCache : passwordResetCaches) {
	    Date createDate = passwordResetCache.getCreatedDate();
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -3);
	    if (createDate.before(cal.getTime())) {
		getCurrentSession().delete(passwordResetCache);
	    }
	}
    }

    @Override
    public void sendFullMemberInvitationReminderToMember() {

	// Query query = getCurrentSession().createQuery(
	// "from MemberInvitation mi");
	//
	// @SuppressWarnings("unchecked")
	// List<MemberInvitation> memberInvitations = query.list();
	// for (MemberInvitation memberInvitation : memberInvitations) {
	// Organisation org = memberInvitation.getOrganisation();
	//
	// Date createDate = memberInvitation.getCreatedDate();
	// Calendar cal = Calendar.getInstance();
	// cal.add(Calendar.DATE, -3);
	// if (isSameDay(createDate, cal.getTime())
	// && MemberInvitationStatus.REQUESTED.equals(memberInvitation
	// .getInvitationStatus())) {
	// MemberInvitationEmail memberInvitationEmail = new
	// MemberInvitationEmail();
	// memberInvitationEmail.setOrganisation(org);
	// memberInvitationEmail.setGuestIdentifier(memberInvitation
	// .getIdentifierOfGuestWantingToBecomeMember());
	// memberInvitationEmail.setEmailAddress(memberInvitation
	// .getEmailAddress());
	// memberInvitationEmail.setIdentifier(memberInvitation
	// .getIdentifier());
	// memberInvitationEmail.setSubject(org.getOrgName()
	// + "'s invitation is waiting for your response");
	// memberInvitationEmail.setCreatedDate(new Date());
	// getCurrentSession().persist(memberInvitationEmail);
	// }
	// }
    }

    private boolean isSameDay(Date date1, Date date2) {
	Calendar cal1 = Calendar.getInstance();
	cal1.setTime(date1);
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);

	if (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
	    if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
		if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
		    return true;
		}
	    }
	}
	return false;
    }
}
