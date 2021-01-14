package com.onionsearchengine.onionmessenger.xmpp.stanzas.csi;

import com.onionsearchengine.onionmessenger.xmpp.stanzas.AbstractStanza;

public class ActivePacket extends AbstractStanza {
	public ActivePacket() {
		super("active");
		setAttribute("xmlns", "urn:xmpp:csi:0");
	}
}
