package hexa.webandrioz.com.congress.model;

/**
 * Created by gipsy_danger on 23/11/16.
 */

public class ByStatesModel {
    String name,imageUrl;
    public ByStatesModel(String name, String imageUrl){
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
