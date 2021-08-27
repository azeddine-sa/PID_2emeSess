package be.iccbxl.pid.controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import be.iccbxl.pid.model.Representation;
import be.iccbxl.pid.model.RepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;

@RestController
public class FeedController {

    @Autowired
    RepresentationService repService;

    @GetMapping(path = "/rss")
    public Channel rss() {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Reservation spectacles Feed");
        channel.setDescription("Differents Spectacles");
        channel.setLink("http://localhost:8080/");
        channel.setUri("http://localhost:8080/");
        channel.setGenerator("In ICC Programming");

        /*Image image = new Image();
        image.setUrl("");
        image.setTitle("Reservation Feed");
        image.setHeight(32);
        image.setWidth(32);
        channel.setImage(image);*/

        Date postDate = new Date();
        channel.setPubDate(postDate);

        List<Representation> list = repService.getLastShows(3);

        List<Item> items = new ArrayList<>();

        for(Representation rep:list){
            Item item = new Item();
            item.setAuthor("system");
            item.setLink("http://localhost:8080/representations/" + rep.getId());
            item.setTitle(rep.getShow().getTitle());
            item.setUri("http://localhost:8080/representations/" + rep.getId());

        com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
        category.setValue("Representation");
        item.setCategories(Collections.singletonList(category));

        Description descr = new Description();
        descr.setValue(rep.getShow().getDescription());
        item.setDescription(descr);
        item.setPubDate(Date.from(rep.getWhen().atZone(ZoneId.systemDefault()).toInstant()));
        items.add(item);
        }

        channel.setItems(items);

        return channel;
    }
}
