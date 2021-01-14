package com.onionsearchengine.onionmessenger.xmpp;

import com.onionsearchengine.onionmessenger.entities.Contact;

public interface OnContactStatusChanged {
	public void onContactStatusChanged(final Contact contact, final boolean online);
}
