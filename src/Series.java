import java.time.LocalDate;

public class Series {
    private String seriesName;
    private String seriesDesc;
    private Episodes[] episodes;
    private int countWatched = 0;

    public Series(String seriesName, String seriesDesc, Episodes[] episodes) {
        this.seriesName = seriesName;
        this.seriesDesc = seriesDesc;
        this.episodes = episodes;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesDesc() {
        return seriesDesc;
    }

    public void setSeriesDesc(String seriesDesc) {
        this.seriesDesc = seriesDesc;
    }

    public Episodes[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episodes[] episodes) {
        this.episodes = episodes;
    }

    public int getCountWatched() {
        return countWatched;
    }

    public void setCountWatched(int countWatched) {
        this.countWatched = countWatched;
    }


}
