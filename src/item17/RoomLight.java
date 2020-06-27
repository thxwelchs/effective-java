package item17;

public class RoomLight {
    private final boolean firstLight;
    private final boolean secondtLight;
    private final boolean thirdLight;

    private RoomLight(boolean firstLight, boolean secondtLight, boolean thirdLight) {
        this.firstLight = firstLight;
        this.secondtLight = secondtLight;
        this.thirdLight = thirdLight;
    }

    public static RoomLight valueOf(boolean firstLight, boolean secondtLight, boolean thirdLight) {
        return new RoomLight(firstLight, secondtLight, thirdLight);
    }

    public RoomLight offFirstLight(RoomLight r) {
        return new RoomLight(false, r.secondtLight, r.thirdLight);
    }

    public boolean isFirstLight() {
        return firstLight;
    }

    public boolean isSecondtLight() {
        return secondtLight;
    }

    public boolean isThirdLight() {
        return thirdLight;
    }
}
