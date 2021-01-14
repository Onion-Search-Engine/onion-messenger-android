package com.onionsearchengine.onionmessenger.xmpp.jingle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.onionsearchengine.onionmessenger.entities.Contact;
import com.onionsearchengine.onionmessenger.entities.Presence;
import com.onionsearchengine.onionmessenger.entities.Presences;
import com.onionsearchengine.onionmessenger.entities.ServiceDiscoveryResult;
import com.onionsearchengine.onionmessenger.xml.Namespace;

public class RtpCapability {

    private static List<String> BASIC_RTP_REQUIREMENTS = Arrays.asList(
            Namespace.JINGLE,
            Namespace.JINGLE_TRANSPORT_ICE_UDP,
            Namespace.JINGLE_APPS_RTP,
            Namespace.JINGLE_APPS_DTLS
    );
    private static List<String> VIDEO_REQUIREMENTS = Arrays.asList(
            Namespace.JINGLE_FEATURE_AUDIO,
            Namespace.JINGLE_FEATURE_VIDEO
    );

    public static Capability check(final Presence presence) {
        final ServiceDiscoveryResult disco = presence.getServiceDiscoveryResult();
        final List<String> features = disco == null ? Collections.emptyList() : disco.getFeatures();
        if (features.containsAll(BASIC_RTP_REQUIREMENTS)) {
            if (features.containsAll(VIDEO_REQUIREMENTS)) {
                return Capability.VIDEO;
            }
            if (features.contains(Namespace.JINGLE_FEATURE_AUDIO)) {
                return Capability.AUDIO;
            }
        }
        return Capability.NONE;
    }

    public static String[] filterPresences(final Contact contact, Capability required) {
        final Presences presences = contact.getPresences();
        final ArrayList<String> resources = new ArrayList<>();
        for(final Map.Entry<String,Presence> presence : presences.getPresencesMap().entrySet()) {
            final Capability capability = check(presence.getValue());
            if (capability == Capability.NONE) {
                continue;
            }
            if (required == Capability.AUDIO || capability == required) {
                resources.add(presence.getKey());
            }
        }
        return resources.toArray(new String[0]);
    }

    public static Capability check(final Contact contact) {
        final Presences presences = contact.getPresences();
        Capability result = Capability.NONE;
        for(Presence presence : presences.getPresences()) {
            Capability capability = check(presence);
            if (capability == Capability.VIDEO) {
                result = capability;
            } else if (capability == Capability.AUDIO && result == Capability.NONE) {
                result = capability;
            }
        }
        return result;
    }

    public enum Capability {
        NONE, AUDIO, VIDEO
    }

}
