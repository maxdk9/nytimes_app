package mazzy.and.nytimes_app.model;

import com.google.gson.annotations.SerializedName;

public class MediaMetadata {
    @SerializedName("format")
    String format;
    @SerializedName("height")
    int height;
    @SerializedName("url")
    String url;
    @SerializedName("width")
    int width;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
