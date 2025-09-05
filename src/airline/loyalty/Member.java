
package airline.loyalty;

public class Member {
    protected String memberId;
    protected String name;
    protected String email;
    protected String tier;
    protected int milesBalance;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.tier = "Silver"; // default tier
        this.milesBalance = 0;
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getTier() { return tier; }
    public int getMilesBalance() { return milesBalance; }

    // Encapsulation: protect balance
    protected void addMiles(int miles) { this.milesBalance += miles; }
    protected void deductMiles(int miles) { this.milesBalance -= miles; }

    // Overloading - credit miles
    public void creditMiles(int distance) {
        int miles = calculateBonus(distance);
        addMiles(miles);
    }

    public void creditMiles(int distance, String fareClass) {
        int bonusFactor = fareClass.equalsIgnoreCase("Business") ? 2 : 1;
        int miles = calculateBonus(distance * bonusFactor);
        addMiles(miles);
    }

    // Overridable
    protected int calculateBonus(int distance) { return distance; }

    public void evaluateTier() {
        if (milesBalance >= 50000) tier = "Platinum";
        else if (milesBalance >= 25000) tier = "Gold";
        else tier = "Silver";
    }

    public void printStatement() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Tier: " + tier);
        System.out.println("Miles Balance: " + milesBalance);
        System.out.println("------------------------");
    }
}

// -------------------- Inheritance -----------------------

class SilverMember extends Member {
    public SilverMember(String memberId, String name, String email) {
        super(memberId, name, email);
        this.tier = "Silver";
    }

    @Override
    protected int calculateBonus(int distance) { return distance; }

    @Override
    public void evaluateTier() {
        if (milesBalance >= 25000) {
            System.out.println("Upgrading to Gold!");
            this.tier = "Gold";
        }
    }
}

class GoldMember extends Member {
    public GoldMember(String memberId, String name, String email) {
        super(memberId, name, email);
        this.tier = "Gold";
    }

    @Override
    protected int calculateBonus(int distance) { return (int)(distance * 1.25); }

    @Override
    public void evaluateTier() {
        if (milesBalance >= 50000) {
            System.out.println("Upgrading to Platinum!");
            this.tier = "Platinum";
        }
    }
}

class PlatinumMember extends Member {
    public PlatinumMember(String memberId, String name, String email) {
        super(memberId, name, email);
        this.tier = "Platinum";
    }

    @Override
    protected int calculateBonus(int distance) { return (int)(distance * 1.5); }

    @Override
    public void evaluateTier() {
        // Platinum is max tier
    }
}
