package mazzy.and.nytimes_app.model;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("abstract")
    private  String abstr;
    @SerializedName("adx_keywords")
    private String adx_keywords;
    @SerializedName("asset_id")
    private Long asset_id;
    @SerializedName("byline")
    private String byline;
    @SerializedName("column")
    private String column;
    @SerializedName("count_type")
    private  String count_type;
    @SerializedName("des_facet")
    private Object des_facet;
    @SerializedName("email_count")
    private Integer email_count;
    @SerializedName("eta_id")
    private Integer eta_id;

    @SerializedName("id")
    private Long id;
    @SerializedName("media")
    private Media[] media;
    @SerializedName("nytdsection")
    private String nytdsection;
    @SerializedName("org_facet")
    private Object org_facet;
    @SerializedName("per_facet")
    private Object per_facet;
    @SerializedName("published_date")
    private String published_date;
    @SerializedName("section")
    private String section;
    @SerializedName("source")
    private String source;
    @SerializedName("subsection")
    private String subsection;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("updated")
    private String updated;
    @SerializedName("uri")
    private String uri;
    @SerializedName("url")
    private String url;


    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public void setAdx_keywords(String adx_keywords) {
        this.adx_keywords = adx_keywords;
    }

    public Long getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(Long asset_id) {
        this.asset_id = asset_id;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCount_type() {
        return count_type;
    }

    public void setCount_type(String count_type) {
        this.count_type = count_type;
    }

    public Object getDes_facet() {
        return des_facet;
    }

    public void setDes_facet(String[] des_facet) {
        this.des_facet = des_facet;
    }

    public Integer getEmail_count() {
        return email_count;
    }

    public void setEmail_count(Integer email_count) {
        this.email_count = email_count;
    }

    public Integer getEta_id() {
        return eta_id;
    }

    public void setEta_id(Integer eta_id) {
        this.eta_id = eta_id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Media[] getMedia() {
        return media;
    }

    public void setMedia(Media[] media) {
        this.media = media;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public void setNytdsection(String nytdsection) {
        this.nytdsection = nytdsection;
    }

    public Object getOrg_facet() {
        return org_facet;
    }

    public void setOrg_facet(String[] org_facet) {
        this.org_facet = org_facet;
    }

    public Object getPer_facet() {
        return per_facet;
    }

    public void setPer_facet(String[] per_facet) {
        this.per_facet = per_facet;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
