package com.onionsearchengine.onionmessenger.xmpp;

import com.onionsearchengine.onionmessenger.crypto.axolotl.AxolotlService;

public interface OnKeyStatusUpdated {
	public void onKeyStatusUpdated(AxolotlService.FetchStatus report);
}
