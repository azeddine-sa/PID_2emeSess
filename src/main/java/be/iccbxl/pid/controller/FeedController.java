package be.iccbxl.pid.controller;

import java.util.Collections;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;

@RestController
public class FeedController {

    @GetMapping(path = "/rss")
    public Channel rss() {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Reservation spectacles Feed");
        channel.setDescription("Differents Spectacles");
        channel.setLink("https://Reservationspectales.com");
        channel.setUri("https://Reservationspectales.com");
        channel.setGenerator("In ICC Programming");

        Image image = new Image();
        image.setUrl("https://Reservationspectales.png");
        image.setTitle("Reservation Feed");
        image.setHeight(32);
        image.setWidth(32);
        channel.setImage(image);

        Date postDate = new Date();
        channel.setPubDate(postDate);

        Item item = new Item();
        item.setAuthor("Saouti & Bennour");
        item.setLink("https://Reservationspectales.com/");
        item.setTitle("Spring CORS Configuration Examples");
        item.setUri("https://Reservationspectales.com/");
        item.setComments("https://Reservationspectales.com/");

        com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
        category.setValue("CORS");
        item.setCategories(Collections.singletonList(category));

        Description descr = new Description();
        descr.setValue("CORS");
        item.setDescription(descr);
        item.setPubDate(postDate);

        channel.setItems(Collections.singletonList(item));

        return channel;
    }
}
