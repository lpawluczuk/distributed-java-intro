package exercise3;

import common.StringUtils;
import common.html.GazetaHtmlDocument;
import java.util.concurrent.Callable;

public class WordCounter implements Callable<Integer> {

    private final String documentUrl;
    private final String wordToCount;

    public WordCounter(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }

    public Integer call() throws Exception {
        return StringUtils.countOccurrences(new GazetaHtmlDocument(documentUrl).getContent().toLowerCase(), wordToCount);
    }
}
