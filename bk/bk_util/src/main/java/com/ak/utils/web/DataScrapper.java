package com.ak.utils.web;

import java.io.IOException;
import java.util.List;

import exceptions.IsbnNotFoundException;

import org.springframework.stereotype.Component;

import com.ak.entities.Bookdetail;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class DataScrapper {
    private WebClient webclient = WebClientProvider.getDefaultWebClient();

    private static final String SEARCH_URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&isbn=%s&new_used=*&destination=by&currency=USD&mode=basic&st=sr&ac=qr";

    private static final String IMAGE_URL = "https://pictures.abebooks.com/isbn/%s-us-300.jpg";

    public Bookdetail getBookFromWeb(String isbn) throws IsbnNotFoundException, IOException {
	Bookdetail bookdetail = new Bookdetail();
	    String url = String.format(SEARCH_URL, isbn);
	    HtmlPage bookPage = webclient.getPage(url);
	    String isbnByPage = bookPage.getElementsByTagName("h1").stream().findFirst().map(DomElement::getTextContent).orElse("");
	    if (isbnByPage.indexOf(isbn) != -1) {
		bookdetail.setIsbn(isbn);
		bookdetail.setDescription(getByXPath(bookPage, "//div[@id='bookSummary']"));
		bookdetail.setDescription(getByXPath(bookPage, "//div[@id='bookSummary']"));
		bookdetail.setCover(String.format(IMAGE_URL, isbn));
		bookdetail.setTitle(getByXPath(bookPage, "//span[@id='describe-isbn-title']"));
		bookdetail.setPublisher(getByXPath(bookPage, "//span[@itemprop='publisher']"));
		bookdetail.setAuthor(getByXPath(bookPage, "//span[@itemprop='author']"));
	    } else {
		throw new IsbnNotFoundException();
	    }
	return bookdetail;
    }

    public String getByXPath(HtmlPage bookPage, String xPath) {
	List<HtmlElement> htmlElements = bookPage.getByXPath(xPath);
	return htmlElements.isEmpty() ? null : htmlElements.get(0).getTextContent();
    }

}
