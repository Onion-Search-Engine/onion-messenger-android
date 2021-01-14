package com.onionsearchengine.onionmessenger.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.widget.Toast;

import java.util.Collections;

import com.onionsearchengine.onionmessenger.R;
import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.entities.Blockable;
import com.onionsearchengine.onionmessenger.entities.ListItem;
import com.onionsearchengine.onionmessenger.entities.RawBlockable;
import com.onionsearchengine.onionmessenger.ui.interfaces.OnBackendConnected;
import com.onionsearchengine.onionmessenger.xmpp.OnUpdateBlocklist;
import com.onionsearchengine.onionmessenger.xmpp.Jid;

public class BlocklistActivity extends AbstractSearchableListItemActivity implements OnUpdateBlocklist {

	private Account account = null;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setOnItemLongClickListener((parent, view, position, id) -> {
			BlockContactDialog.show(BlocklistActivity.this, (Blockable) getListItems().get(position));
			return true;
		});
		this.binding.fab.show();
		this.binding.fab.setOnClickListener((v)->showEnterJidDialog());
	}

	@Override
	public void onBackendConnected() {
		for (final Account account : xmppConnectionService.getAccounts()) {
			if (account.getJid().toEscapedString().equals(getIntent().getStringExtra(EXTRA_ACCOUNT))) {
				this.account = account;
				break;
			}
		}
		filterContacts();
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DIALOG);
		if (fragment instanceof OnBackendConnected) {
			((OnBackendConnected) fragment).onBackendConnected();
		}
	}

	@Override
	protected void filterContacts(final String needle) {
		getListItems().clear();
		if (account != null) {
			for (final Jid jid : account.getBlocklist()) {
				ListItem item;
				if (jid.isFullJid()) {
					item = new RawBlockable(account, jid);
				} else {
					item = account.getRoster().getContact(jid);
				}
				if (item.match(this, needle)) {
					getListItems().add(item);
				}
			}
			Collections.sort(getListItems());
		}
		getListItemAdapter().notifyDataSetChanged();
	}

	protected void showEnterJidDialog() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		EnterJidDialog dialog = EnterJidDialog.newInstance(
				null,
				getString(R.string.block_jabber_id),
				getString(R.string.block),
				null,
				account.getJid().asBareJid().toEscapedString(),
				true,
				false
		);

		dialog.setOnEnterJidDialogPositiveListener((accountJid, contactJid) -> {
			Blockable blockable = new RawBlockable(account, contactJid);
			if (xmppConnectionService.sendBlockRequest(blockable, false)) {
				Toast.makeText(BlocklistActivity.this, R.string.corresponding_conversations_closed, Toast.LENGTH_SHORT).show();
			}
			return true;
		});

		dialog.show(ft, "dialog");
	}

	protected void refreshUiReal() {
		final Editable editable = getSearchEditText().getText();
		if (editable != null) {
			filterContacts(editable.toString());
		} else {
			filterContacts();
		}
	}

	@Override
	public void OnUpdateBlocklist(final OnUpdateBlocklist.Status status) {
		refreshUi();
	}

}
