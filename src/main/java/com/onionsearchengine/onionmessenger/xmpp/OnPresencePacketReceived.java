package com.onionsearchengine.onionmessenger.xmpp;

import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.xmpp.stanzas.PresencePacket;

public interface OnPresencePacketReceived extends PacketReceived {
	public void onPresencePacketReceived(Account account, PresencePacket packet);
}
