package org.example;

import dev.langchain4j.data.document.Document;

import dev.langchain4j.web.search.*;

import java.net.URI;

import java.net.URISyntaxException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.function.Consumer;

public class HugBase3 implements WebSearchEngine {

    HugBase3() throws URISyntaxException {

        WebSearchResults webSearchResults =  search("hello");
        if(webSearchResults != null) {
            System.out.println("===" +
                    "====" + webSearchResults.toString());
        }
        else {

            Map<String, Object> result = new HashMap<>();

            result.put("ok", "https://www.msn.com/en-" +
                    "us/news/technology/new-foldable-iphone-rumors-predict-apple-s-bold-plans-here-are-6-things-to-expect-from-c" +
                    "ameras-to-launch-date/ar-AA1AoMxB?ocid=msedgntp&pc=HCTS&cvid=1471cf1b42d648b6854ac6199e91c271&ei=13");

            List<WebSearchOrganicResult> list = new ArrayList<>();

            list.add(new WebSearchOrganicResult("Best results", new URI( "https://www.msn.com/en-" +
                    "us/news/technology/new-foldable-iphone-rumors-predict-apple-s-bold-plans-here-are-6-things-to-expect-from-c" +
                    "ameras-to-launch-date/ar-AA1AoMxB?ocid=msedgntp&pc=HCTS&cvid=1471cf1b42d648b6854ac6199e91c271&ei=13")));

            WebSearchResults web = new WebSearchResults(result,new WebSearchInformationResult(20L), list);
/*
            System.out.println("===" + web.searchInformation());
            System.out.println("===" + web.toDocuments().get(0).text());
            System.out.println("===" + web);*/
            web.toDocuments().stream().iterator().forEachRemaining(new Consumer<Document>() {
                @Override
                public void accept(Document document) {
                    System.out.println("Text: " + document.text());
                    System.out.println("---");
                }
            });
            web.results().stream().iterator().forEachRemaining(new Consumer<WebSearchOrganicResult>() {
                @Override
                public void accept(WebSearchOrganicResult webSearchOrganicResult) {
                    System.out.println("URL: " + webSearchOrganicResult.url());
                    System.out.println("Text: " + webSearchOrganicResult.content());
                    System.out.println("Description: " + webSearchOrganicResult.snippet());
                    System.out.println("Image: " + webSearchOrganicResult.title());
                    System.out.println("---");
                }
            });
        }
    }

    @Override
    public WebSearchResults search(String query) {
        return WebSearchEngine.super.search(query);
    }

    @Override
    public WebSearchResults search(WebSearchRequest webSearchRequest) {
        return null;
    }
}
