/*
 * Copyright 2023 Marc Liberatore.
 */

package comparators;

import java.time.Instant;

public class WebPageRecord implements Comparable<WebPageRecord> {
    public final String URL;
    public final Instant lastRetrieved;
    public final int length;
    public final String firstLine;

    public WebPageRecord(String URL, Instant lastRetrieved, int length, String firstLine) {
        this.URL = URL;
        this.lastRetrieved = lastRetrieved;
        this.length = length;
        this.firstLine = firstLine;
    }

    @Override
    public String toString() {
        return "WebPageRecord [URL=" + URL + ", lastRetrieved=" + lastRetrieved + ", length=" + length + ", firstLine="
                + firstLine + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((URL == null) ? 0 : URL.hashCode());
        result = prime * result + ((lastRetrieved == null) ? 0 : lastRetrieved.hashCode());
        result = prime * result + length;
        result = prime * result + ((firstLine == null) ? 0 : firstLine.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebPageRecord other = (WebPageRecord) obj;
        if (URL == null) {
            if (other.URL != null)
                return false;
        } else if (!URL.equals(other.URL))
            return false;
        if (lastRetrieved == null) {
            if (other.lastRetrieved != null)
                return false;
        } else if (!lastRetrieved.equals(other.lastRetrieved))
            return false;
        if (length != other.length)
            return false;
        if (firstLine == null) {
            if (other.firstLine != null)
                return false;
        } else if (!firstLine.equals(other.firstLine))
            return false;
        return true;
    }

    /**
     * Compare this WebPageRecord with another to determine order. The order should
     * be determined by the lexicographic ordering of the WebPageRecords' URLs; if
     * those are equal, then break ties using the lastRetrieved value's natural
     * order.
     */
    @Override
    public int compareTo(WebPageRecord w) {
        int equal = this.URL.compareTo(w.URL);
        if (equal != 0)
            return equal;
        return this.lastRetrieved.compareTo(w.lastRetrieved);
    }

}
