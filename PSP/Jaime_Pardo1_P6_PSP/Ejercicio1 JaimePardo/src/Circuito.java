public class Circuito {
    private float lon;
    private float lat;
    private int zoom;
    private String location;
    private String name;
    private String id;

    public Circuito(float lon, float lat, int zoom, String location, String name, String id) {
        this.lon = lon;
        this.lat = lat;
        this.zoom = zoom;
        this.location = location;
        this.name = name;
        this.id = id;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
