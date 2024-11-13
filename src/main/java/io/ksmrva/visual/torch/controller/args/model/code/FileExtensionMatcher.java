package io.ksmrva.visual.torch.controller.args.model.code;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FileExtensionMatcher {

    private static final Logger LOGGER = LogManager.getLogger(FileExtensionMatcher.class);

    public enum MatchType {
        BLACKLIST, WHITELIST
    }

    public List<String> extensionsToMatch;

    public MatchType matchType;

    public FileExtensionMatcher() {
        this.extensionsToMatch = new ArrayList<>();
        matchType = MatchType.BLACKLIST;
    }

    public boolean isAcceptable(String extension) {
        boolean isAcceptable;

        boolean extensionMatch = false;
        if (extensionsToMatch.contains(extension)) {
            extensionMatch = true;
        }

        if (matchType.equals(MatchType.BLACKLIST)) {
            if (extensionMatch) {
                isAcceptable = false;
            } else {
                isAcceptable = true;
            }
        } else if (matchType.equals(MatchType.WHITELIST)) {
            if (extensionMatch) {
                isAcceptable = true;
            } else {
                isAcceptable = false;
            }
        } else {
            LOGGER.warn("Unknown match type [" + matchType + "]; allowing all extensions");
            isAcceptable = true;
        }
        return isAcceptable;
    }

    public List<String> getExtensionsToMatch() {
        return extensionsToMatch;
    }

    public void setExtensionsToMatch(List<String> extensionsToMatch) {
        if (!CollectionUtils.isEmpty(extensionsToMatch)) {
            this.extensionsToMatch = extensionsToMatch;
        }
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

}
