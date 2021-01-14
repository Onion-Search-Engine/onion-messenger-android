package com.onionsearchengine.onionmessenger.entities;

import com.onionsearchengine.onionmessenger.xmpp.Jid;

public interface Blockable {
	boolean isBlocked();
	boolean isDomainBlocked();
	Jid getBlockedJid();
	Jid getJid();
	Account getAccount();
}
