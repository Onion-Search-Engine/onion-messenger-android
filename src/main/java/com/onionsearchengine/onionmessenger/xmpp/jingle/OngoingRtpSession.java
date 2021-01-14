package com.onionsearchengine.onionmessenger.xmpp.jingle;

import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.xmpp.Jid;

public interface OngoingRtpSession {
    Account getAccount();
    Jid getWith();
    String getSessionId();
}
