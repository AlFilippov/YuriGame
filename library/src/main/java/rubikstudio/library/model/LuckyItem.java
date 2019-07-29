package rubikstudio.library.model;
public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int secondaryTextOrientation;
    public int icon;
    public int color;

    public LuckyItem(String topText, int icon, int color) {
        this.topText = topText;
        this.icon = icon;
        this.color = color;
    }
}
