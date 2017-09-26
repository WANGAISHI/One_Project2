package bean;

import java.util.List;

/**
 * Created by 王爱诗 on 2017/9/26.
 */

public class Bean2 {

            public String title;
            public String thumbnail_pic_s;

    public Bean2() {
        super();
    }

    @Override
    public String toString() {
        return "Bean2{" +
                "title='" + title + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public Bean2(String title, String thumbnail_pic_s) {
        this.title = title;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
