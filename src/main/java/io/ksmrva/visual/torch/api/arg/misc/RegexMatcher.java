package io.ksmrva.visual.torch.api.arg.misc;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RegexMatcher<T> {

    private static final Logger LOGGER = LogManager.getLogger(RegexMatcher.class);

    private T objectForMatch;

    private List<String> matchingRegexes;

    public RegexMatcher() {
        this.objectForMatch = null;
        this.matchingRegexes = new ArrayList<>();
    }

    public void addRegex(String regex) {
        if (!StringUtils.isEmpty(regex)) {
            this.matchingRegexes.add(regex);
        } else {
            LOGGER.warn("Was provided a null/empty regex to add, ignoring");
        }
    }

    public T getObjectForMatch() {
        return objectForMatch;
    }

    public void setObjectForMatch(T objectForMatch) {
        this.objectForMatch = objectForMatch;
    }

    public List<String> getMatchingRegexes() {
        return matchingRegexes;
    }

    public void setMatchingRegexes(List<String> matchingRegexes) {
        this.matchingRegexes = matchingRegexes;
    }

    public boolean doesValueMatch(String value) {
        boolean doesValueMatch = false;
        if (value != null) {
            for (String matchingRegex : matchingRegexes) {
                if (value.matches(matchingRegex)) {
                    doesValueMatch = true;
                    break;
                }
            }
        }
        return doesValueMatch;
    }

}
