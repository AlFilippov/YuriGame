package rubikstudio.library.model;
public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int score;
    public int secondaryTextOrientation;
    public int icon;
    public int color;

    public LuckyItem(int score, int icon, int color) {
        this.score = score;
        this.icon = icon;
        this.color = color;
    }


}
