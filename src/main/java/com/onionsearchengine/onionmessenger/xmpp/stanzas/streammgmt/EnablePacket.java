package com.onionsearchengine.onionmessenger.xmpp.stanzas.streammgmt;

import com.onionsearchengine.onionmessenger.xmpp.stanzas.AbstractStanza;

public class EnablePacket extends AbstractStanza {

	public EnablePacket(int smVersion) {
		super("enable");
		this.setAttribute("xmlns", "urn:xmpp:sm:" + smVersion);
		this.setAttribute("resume", "true");
	}

}
