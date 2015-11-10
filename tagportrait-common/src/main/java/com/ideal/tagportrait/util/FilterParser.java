package com.ideal.tagportrait.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by zhou on 2015/10/29.
 */
public class FilterParser {
    private int charIndex = 0;
    private int filterIndex = 0;
    private String filterStr;
    private String[] filterArray;
    private final String RULE_REGEX = "\\$|\\||!";

    public FilterParser(String filterStr) {
        this.filterStr = filterStr;
        filterArray = this.filterStr.split(RULE_REGEX);
    }

    public String getNext() {
        String f = filterArray[filterIndex];
        filterIndex++;
        charIndex += f.length() + 1;
        return f;
    }

    public boolean hasNext() {
        return filterIndex != filterArray.length;
    }

    public Rule getNextRule() {
        if (charIndex == 0) {
            return Rule.OR;
        }
        char c = this.filterStr.charAt(charIndex - 1);
        switch (c) {
            case '$':
                return Rule.AND;
            case '|':
                return Rule.OR;
            case '!':
                return Rule.NOT;
        }
        throw new RuntimeException("--未知运算符：" + c);
    }

    public enum Rule {
        AND {
            @Override
            public void comp(HashSet<String> rs, Collection r) {
                if (rs.isEmpty()) {
                    return;
                }
                if (r == null || r.isEmpty()) {
                    rs.clear();
                }
                Iterator<String> i = rs.iterator();
                while (i.hasNext()) {
                    String s = i.next();
                    if (!r.contains(s)) {
                        i.remove();
                    }
                }
            }
        }, OR {
            @Override
            public void comp(HashSet<String> rs, Collection r) {
                if (r == null || r.isEmpty()) {
                    return;
                }
                rs.addAll(r);
            }
        }, NOT {
            @Override
            public void comp(HashSet<String> rs, Collection r) {
                if (r == null || r.isEmpty() || rs.isEmpty()) return;
                for (String s : (Collection<String>) r) {
                    if (rs.contains(s)) {
                        rs.remove(s);
                    }
                }
            }
        };

        public abstract void comp(HashSet<String> rs, Collection r);
    }
}


