package com.onionsearchengine.onionmessenger.services;

public class QuickConversationsService extends AbstractQuickConversationsService {

    QuickConversationsService(XmppConnectionService xmppConnectionService) {
        super(xmppConnectionService);
    }

    @Override
    public void considerSync() {

    }

    @Override
    public void signalAccountStateChange() {

    }

    @Override
    public boolean isSynchronizing() {
        return false;
    }

    @Override
    public void considerSyncBackground(boolean force) {

    }
}