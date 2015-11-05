package org.iblitzc0de.movielist.apis.daos;

import org.iblitzc0de.movielist.provider.video.VideoCursor;

public class VideoDao {
    private String id;
    private String iso_639_1;
    private String key;
    private String name;
    private String site;
    private int size;
    private String type;

    public VideoDao(VideoCursor c) {
        this.id = c.getVideoId();
        this.key = c.getKey();
        this.name = c.getName();
        this.size = c.getSize().intValue();
        this.type = c.getType();
    }

    public String getId() {
        return this.id;
    }

    public String getIso_639_1() {
        return this.iso_639_1;
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public String getSite() {
        return this.site;
    }

    public int getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }
}
