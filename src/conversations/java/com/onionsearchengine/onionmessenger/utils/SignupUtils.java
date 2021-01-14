package com.onionsearchengine.onionmessenger.utils;

import android.app.Activity;
import android.content.Intent;

import com.onionsearchengine.onionmessenger.Config;
import com.onionsearchengine.onionmessenger.entities.Account;
import com.onionsearchengine.onionmessenger.services.XmppConnectionService;
import com.onionsearchengine.onionmessenger.ui.ConversationsActivity;
import com.onionsearchengine.onionmessenger.ui.EditAccountActivity;
import com.onionsearchengine.onionmessenger.ui.MagicCreateActivity;
import com.onionsearchengine.onionmessenger.ui.ManageAccountActivity;
import com.onionsearchengine.onionmessenger.ui.PickServerActivity;
import com.onionsearchengine.onionmessenger.ui.StartConversationActivity;
import com.onionsearchengine.onionmessenger.ui.WelcomeActivity;
import com.onionsearchengine.onionmessenger.xmpp.Jid;

public class SignupUtils {

    public static boolean isSupportTokenRegistry() {
        return true;
    }

    public static Intent getTokenRegistrationIntent(final Activity activity, Jid jid, String preAuth) {
        final Intent intent = new Intent(activity, MagicCreateActivity.class);
        if (jid.isDomainJid()) {
            intent.putExtra(MagicCreateActivity.EXTRA_DOMAIN, jid.getDomain().toEscapedString());
        } else {
            intent.putExtra(MagicCreateActivity.EXTRA_DOMAIN, jid.getDomain().toEscapedString());
            intent.putExtra(MagicCreateActivity.EXTRA_USERNAME, jid.getEscapedLocal());
        }
        intent.putExtra(MagicCreateActivity.EXTRA_PRE_AUTH, preAuth);
        return intent;
    }

    public static Intent getSignUpIntent(final Activity activity) {
        return getSignUpIntent(activity, false);
    }

    public static Intent getSignUpIntent(final Activity activity, final boolean toServerChooser) {
        final Intent intent;
        if (toServerChooser) {
            intent = new Intent(activity, PickServerActivity.class);
        } else {
            intent = new Intent(activity, WelcomeActivity.class);
        }
        return intent;
    }

    public static Intent getRedirectionIntent(final ConversationsActivity activity) {
        final XmppConnectionService service = activity.xmppConnectionService;
        Account pendingAccount = AccountUtils.getPendingAccount(service);
        Intent intent;
        if (pendingAccount != null) {
            intent = new Intent(activity, EditAccountActivity.class);
            intent.putExtra("jid", pendingAccount.getJid().asBareJid().toString());
            if (!pendingAccount.isOptionSet(Account.OPTION_MAGIC_CREATE)) {
                intent.putExtra(EditAccountActivity.EXTRA_FORCE_REGISTER, pendingAccount.isOptionSet(Account.OPTION_REGISTER));
            }
        } else {
            if (service.getAccounts().size() == 0) {
                if (Config.X509_VERIFICATION) {
                    intent = new Intent(activity, ManageAccountActivity.class);
                } else if (Config.MAGIC_CREATE_DOMAIN != null) {
                    intent = getSignUpIntent(activity);
                } else {
                    intent = new Intent(activity, EditAccountActivity.class);
                }
            } else {
                intent = new Intent(activity, StartConversationActivity.class);
            }
        }
        intent.putExtra("init", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}