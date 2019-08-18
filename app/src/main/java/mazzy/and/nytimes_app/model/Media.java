package mazzy.and.nytimes_app.model;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("approved_for_syndication")
    private
    Integer approved_for_syndication;
    @SerializedName("caption")
    private
    String caption;
    @SerializedName("copyright")
    private
    String copyright;
    @SerializedName("media-metadata")
    private
    MediaMetadata [] mediaMetadata;
    @SerializedName("subtype")
    private
    String subtype;
    @SerializedName("type")
    private
    String type;

    public Integer isApproved_for_syndication() {
        return approved_for_syndication;
    }

    public void setApproved_for_syndication(Integer approved_for_syndication) {
        this.approved_for_syndication = approved_for_syndication;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public MediaMetadata[] getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(MediaMetadata[] mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
