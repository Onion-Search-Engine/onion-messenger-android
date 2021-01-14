package com.onionsearchengine.onionmessenger.xmpp.jingle;

import com.onionsearchengine.onionmessenger.entities.DownloadableFile;

public interface OnFileTransmissionStatusChanged {
	void onFileTransmitted(DownloadableFile file);

	void onFileTransferAborted();
}
