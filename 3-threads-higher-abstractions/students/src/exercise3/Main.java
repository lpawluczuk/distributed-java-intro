package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "sikorski";

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<Integer>> list = new LinkedList<Future<Integer>>();
        for (String link : links) {
            list.add((executorService.submit(new WordCounter(link, wordToFound))));
        }

        executorService.shutdown();
        int numberOfWords = 0;
        for (Future<Integer> future : list) {
            numberOfWords += future.get();
        }

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
