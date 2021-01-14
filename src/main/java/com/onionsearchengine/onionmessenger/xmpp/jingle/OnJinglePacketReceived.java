package com.onionsearchengine.onionmessenger.xmpp.jingle;

import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.xmpp.PacketReceived;
import com.onionsearchengine.onionmessenger.xmpp.jingle.stanzas.JinglePacket;

public interface OnJinglePacketReceived extends PacketReceived {
	void onJinglePacketReceived(Account account, JinglePacket packet);
}
