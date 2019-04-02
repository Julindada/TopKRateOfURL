/**
 * Created by julin on 2019-04-01 16:28
 */
public class URLEntity {
    private String URL;
    private Integer count;

    public URLEntity(String URL, Integer count) {
        this.URL = URL;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "URLEntity{" +
                "URL='" + URL + '\'' +
                ", count=" + count +
                '}';
    }
}
