package com.laotek.churchguru.daos.donation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.shared.enums.DonationThankyouEmailSendStatus;
import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DonationDaoImpl extends BaseSessionFactory implements DonationDao {

    @Override
    public void createDonation(Donation donation) {
	donation.setDonationTransactionStatus(DonationTransactionStatus.CREATED);
	donation.setDonationThankyouEmailSendStatus(DonationThankyouEmailSendStatus.NOT_SENT);
	donation.setCreatedDate(new Date());
	donation.setLastUpdatedDate(new Date());
	getCurrentSession().persist(donation);
    }

    @Override
    public void cancelDonation(String paymentIdentifier) {
	Donation donation = getDonationByPaymentID(paymentIdentifier);
	donation.setDonationTransactionStatus(DonationTransactionStatus.CANCELLED);
	donation.setLastUpdatedDate(new Date());
    }

    @Override
    public void completeDonation(String identifier) {
	Donation donation = getDonationByIdentifier(identifier);
	donation.setDonationThankyouEmailSendStatus(DonationThankyouEmailSendStatus.READY_TO_BE_SENT);
	donation.setDonationTransactionStatus(DonationTransactionStatus.COMPLETED);
	donation.setLastUpdatedDate(new Date());
    }

    private Donation getDonationByPaymentID(String paymentId) {
	Query query = getCurrentSession().createQuery(
		"from Donation d where d.paymentId = :paymentId");
	query.setParameter("paymentId", paymentId);
	Donation donation = (Donation) query.uniqueResult();
	return donation;
    }

    @Override
    public Donation getDonationByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery(
		"from Donation d where d.identifier = :identifier");
	query.setParameter("identifier", identifier);
	Donation donation = (Donation) query.uniqueResult();
	return donation;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Donation> search(String surname, String forenames,
	    List<DonationType> donationTypes,
	    List<DonationTransactionStatus> statuses, Date from, Date to,
	    int maxResult) {

	DetachedCriteria donations = DetachedCriteria.forClass(Donation.class);
	if (StringUtils.hasLength(surname)) {
	    donations.add(Restrictions.like("surname", surname + "%"));
	}

	if (StringUtils.hasLength(forenames)) {
	    donations.add(Restrictions.like("forenames", forenames + "%"));
	}

	if (donationTypes != null && donationTypes.size() > 0) {
	    Disjunction disjunction = Restrictions.disjunction();
	    for (DonationType type : DonationType.values()) {
		if (DonationType.BUILDING_FUND.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("buildingFund"))
			    .add(Restrictions.ne("buildingFund",
				    new BigDecimal("0.00"))));
		} else if (DonationType.OFFERING.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("offering"))
			    .add(Restrictions.ne("offering", new BigDecimal(
				    "0.00"))));
		} else if (DonationType.OTHER.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("otherOffering"))
			    .add(Restrictions.ne("otherOffering",
				    new BigDecimal("0.00"))));
		} else if (DonationType.SPECIAL_OFFERING.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("specialOffering"))
			    .add(Restrictions.ne("specialOffering",
				    new BigDecimal("0.00"))));
		} else if (DonationType.THANKSGIVING.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("thanksGiving"))
			    .add(Restrictions.ne("thanksGiving",
				    new BigDecimal("0.00"))));
		} else if (DonationType.TITHE.equals(type)) {
		    disjunction.add(Restrictions
			    .and()
			    .add(Restrictions.isNotNull("tithe"))
			    .add(Restrictions.ne("tithe",
				    new BigDecimal("0.00"))));
		}
	    }
	    donations.add(disjunction);
	}

	if (statuses != null && statuses.size() > 0) {
	    Disjunction disjunction = Restrictions.disjunction();
	    for (DonationTransactionStatus status : statuses) {
		disjunction.add(Restrictions.eq("donationTransactionStatus",
			status));
	    }
	    donations.add(disjunction);
	}

	if (from != null && to != null) {
	    donations.add(Restrictions.between("createdDate", from, to));
	} else if (from != null) {
	    donations.add(Restrictions.or(Restrictions.eq("createdDate", from),
		    Restrictions.gt("createdDate", from)));
	} else if (to != null) {
	    donations.add(Restrictions.or(Restrictions.eq("createdDate", to),
		    Restrictions.lt("createdDate", to)));
	}
	return donations.getExecutableCriteria(getCurrentSession())
		.setMaxResults(maxResult).list();
    }
    // @Override
    // @SuppressWarnings("unchecked")
    // public List<Donation> search(String surname, String forenames, Date from,
    // Date to) {
    // StringBuffer sb = new StringBuffer("from Donation d ");
    // if (StringUtils.hasLength(surname) || StringUtils.hasLength(forenames)
    // || from != null || to != null) {
    // sb.append("where ");
    // if (StringUtils.hasLength(surname)) {
    // sb.append("d.surname = :surname ");
    // }
    // if (StringUtils.hasLength(forenames)) {
    // sb.append("and d.forenames = :forenames ");
    // }
    // }
    // sb.append("order by createdDate desc");
    // Query query = getCurrentSession().createQuery(sb.toString());
    // return query.list();
    // }

}
