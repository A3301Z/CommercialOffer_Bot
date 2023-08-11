package MainPackage;

public class CostOfEquipment {
    int price = 0;
    public int returnOfPrice(int airVolumeInRoom) {
        if (airVolumeInRoom <= 500) {
            price = 40_000;
        } else if (airVolumeInRoom <= 1000) {
            price = 80_000;
        } else if (airVolumeInRoom <= 1500) {
            price = 100_000;
        } else if (airVolumeInRoom <= 2000) {
            price = 160_000;
        } else if (airVolumeInRoom <= 2500) {
            price = 180_000;
        } else if (airVolumeInRoom <= 3000) {
            price = 200_000;
        } else if (airVolumeInRoom <= 3500) {
            price = 220_000;
        } else if (airVolumeInRoom <= 4000) {
            price = 240_000;
        } else if (airVolumeInRoom <= 5000) {
            price = 260_000;
        } else if (airVolumeInRoom <= 6000) {
            price = 280_000;
        } else if (airVolumeInRoom <= 7500) {
            price = 300_000;
        } else if (airVolumeInRoom <= 8000) {
            price = 320_000;
        }
        return price;
    }
}
