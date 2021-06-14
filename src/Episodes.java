import java.time.LocalDate;

public class Episodes {
    private String episodeName;
    private String episodeDesc;
    private LocalDate dateAired;
    private boolean isWatched;


    public Episodes(String episodeName, String episodeDesc, LocalDate dateAired) {
        this.episodeName = episodeName;
        this.episodeDesc = episodeDesc;
        this.dateAired = dateAired;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeDesc() {
        return episodeDesc;
    }

    public void setEpisodeDesc(String episodeDesc) {
        this.episodeDesc = episodeDesc;
    }

    public LocalDate getDateAired() {
        return dateAired;
    }

    public void setDateAired(LocalDate dateAired) {
        this.dateAired = dateAired;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

}
