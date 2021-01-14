package com.onionsearchengine.onionmessenger.xmpp;

import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.xmpp.stanzas.IqPacket;

public interface OnIqPacketReceived extends PacketReceived {
	void onIqPacketReceived(Account account, IqPacket packet);
}
