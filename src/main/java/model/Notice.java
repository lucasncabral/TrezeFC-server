package model;

/**
 * Created by Lucas on 11/01/2017.
 */
public class Notice {
    private String description, linkImage, publishedBy, linkNotice, date;
    private Boolean errorImage = false;
    private Integer id;

    public void setLinkNotice(String linkNotice) {
        this.linkNotice = linkNotice;
    }

    public Notice(Integer id, String description, String linkImage, String publishedBy, String linkNotice, String date) {
        this.description = description;
        this.linkImage = linkImage;
        this.id = id;
        this.publishedBy = publishedBy;
        this.linkNotice = linkNotice;
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public Boolean getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(Boolean errorImage) {
        this.errorImage = errorImage;
    }

    public String getLinkNotice() {
        return linkNotice;
    }

    public String getDate() {
        return date;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return this.getPublishedBy() + " - " + " - " + this.getLinkImage();
    }
}
