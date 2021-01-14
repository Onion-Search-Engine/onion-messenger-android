package com.onionsearchengine.onionmessenger.xmpp.stanzas.streammgmt;

import com.onionsearchengine.onionmessenger.xmpp.stanzas.AbstractStanza;

public class RequestPacket extends AbstractStanza {

	public RequestPacket(int smVersion) {
		super("r");
		this.setAttribute("xmlns", "urn:xmpp:sm:" + smVersion);
	}

}
