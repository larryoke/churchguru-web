package com.laotek.churchguru.daos.nightly;

public interface NightlyDao {
	
	void cleanUpPasswordResetCache();
	
	void sendFullMemberInvitationReminderToMember();
}
