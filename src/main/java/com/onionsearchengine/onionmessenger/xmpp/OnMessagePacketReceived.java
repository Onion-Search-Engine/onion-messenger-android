package com.onionsearchengine.onionmessenger.xmpp;

import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.xmpp.stanzas.MessagePacket;

public interface OnMessagePacketReceived extends PacketReceived {
	public void onMessagePacketReceived(Account account, MessagePacket packet);
}
