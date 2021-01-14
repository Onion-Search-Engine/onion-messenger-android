package com.onionsearchengine.onionmessenger.xmpp.stanzas.csi;

import com.onionsearchengine.onionmessenger.xmpp.stanzas.AbstractStanza;

public class InactivePacket extends AbstractStanza {
	public InactivePacket() {
		super("inactive");
		setAttribute("xmlns", "urn:xmpp:csi:0");
	}
}
