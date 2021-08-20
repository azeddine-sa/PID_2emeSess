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
        channel.setTitle("Reservation Feed");
        channel.setDescription("Differents Spectacles");
        channel.setLink("https://Reservation.com");
        channel.setUri("https://Reservation.com");
        channel.setGenerator("In ICC Programming");

        Image image = new Image();
        image.setUrl("https://Reservation.png");
        image.setTitle("Reservation Feed");
        image.setHeight(32);
        image.setWidth(32);
        channel.setImage(image);

        Date postDate = new Date();
        channel.setPubDate(postDate);

        Item item = new Item();
        item.setAuthor("Az & Ben");
        item.setLink("https://Reservation.com/");
        item.setTitle("Spring CORS Configuration Examples");
        item.setUri("https://Reservation.com/");
        item.setComments("https://Reservation.com/");

        com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
        category.setValue("CORS");
        item.setCategories(Collections.singletonList(category));

        Description descr = new Description();
        descr.setValue(
                "CORS");
        item.setDescription(descr);
        item.setPubDate(postDate);

        channel.setItems(Collections.singletonList(item));

        return channel;
    }
}
