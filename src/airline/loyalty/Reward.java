package airline.loyalty;

public class Reward {
    public String rewardId;
    private String name;
    private int milesRequired;
    private int availability;

    public Reward(String rewardId, String name, int milesRequired, int availability) {
        this.rewardId = rewardId;
        this.name = name;
        this.milesRequired = milesRequired;
        this.availability = availability;
    }

    public String getName() { return name; }

    // Polymorphism: redeem works differently for each reward type (can extend)
    public boolean redeem(Member m) {
        if (availability <= 0) {
            System.out.println("Reward not available.");
            return false;
        }
        if (m.getMilesBalance() >= milesRequired) {
            try {
                m.getClass().getSuperclass().getDeclaredMethod("deductMiles", int.class).invoke(m, milesRequired);
            } catch (Exception e) {
                e.printStackTrace();
            }
            availability--;
            System.out.println("Reward redeemed: " + name);
            return true;
        } else {
            System.out.println("Not enough miles to redeem " + name);
            return false;
        }
    }
}

