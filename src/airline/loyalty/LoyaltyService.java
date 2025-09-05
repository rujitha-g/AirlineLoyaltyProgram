/*package airline.loyalty;

import java.util.*;

public class LoyaltyService {
    private List<Member> members = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    public Member enroll(String memberId, String name, String email) {
        Member m = new SilverMember(memberId, name, email);
        members.add(m);
        System.out.println("Enrolled new member: " + name);
        return m;
    }

    public void addReward(Reward r) { rewards.add(r); }

    public void creditMiles(Member m, FlightActivity fa) {
        m.creditMiles(fa.getDistance(), fa.getFareClass());
        m.evaluateTier();
        fa.setMilesEarned(fa.getDistance());
    }

    public void redeemReward(Member m, String rewardName) {
        for (Reward r : rewards) {
            if (r.getName().equalsIgnoreCase(rewardName)) {
                r.redeem(m);
                return;
            }
        }
        System.out.println("Reward not found.");
    }

    public void printAllStatements() {
        for (Member m : members) {
            m.printStatement();
        }
    }

    // ------------------- MAIN DEMO -------------------
    public static void main(String[] args) {
        LoyaltyService service = new LoyaltyService();

        // 1. Enroll
        Member m1 = service.enroll("M001", "Alice", "alice@mail.com");
        Member m2 = service.enroll("M002", "Bob", "bob@mail.com");

        // 2. Rewards
        service.addReward(new Reward("R001", "Free Flight", 20000, 5));
        service.addReward(new Reward("R002", "Business Upgrade", 15000, 3));
        service.addReward(new Reward("R003", "Shopping Voucher", 5000, 10));

        // 3. Flights
        FlightActivity f1 = new FlightActivity("F001", "AI101", new Date(), 1200, "Economy");
        FlightActivity f2 = new FlightActivity("F002", "AI202", new Date(), 5000, "Business");

        service.creditMiles(m1, f1);
        service.creditMiles(m1, f2);

        // 4. Redeem
        service.redeemReward(m1, "Shopping Voucher");
        service.redeemReward(m1, "Free Flight");

        // 5. Statements
        service.printAllStatements();
    }
}*/
package airline.loyalty;

import java.util.*;

public class LoyaltyService {
    private List<Member> members = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Member enroll(String memberId, String name, String email) {
        Member m = new SilverMember(memberId, name, email);
        members.add(m);
        System.out.println("✅ Enrolled new member: " + name);
        return m;
    }

    public void addReward(Reward r) { rewards.add(r); }

    public void creditMiles(Member m, FlightActivity fa) {
        m.creditMiles(fa.getDistance(), fa.getFareClass());
        m.evaluateTier();
        fa.setMilesEarned(fa.getDistance());
        System.out.println("✅ Credited " + fa.getDistance() + " miles to " + m.getName());
    }

    public void redeemReward(Member m, String rewardName) {
        for (Reward r : rewards) {
            if (r.getName().equalsIgnoreCase(rewardName)) {
                r.redeem(m);
                return;
            }
        }
        System.out.println("❌ Reward not found.");
    }

    public void printAllStatements() {
        for (Member m : members) {
            m.printStatement();
        }
    }

    public Member findMember(String id) {
        for (Member m : members) {
            if (m.getMemberId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    // ------------------- MAIN MENU -------------------
    public static void main(String[] args) {
        LoyaltyService service = new LoyaltyService();

        // Preload some rewards
        service.addReward(new Reward("R001", "Free Flight", 20000, 5));
        service.addReward(new Reward("R002", "Business Upgrade", 15000, 3));
        service.addReward(new Reward("R003", "Shopping Voucher", 5000, 10));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Airline Loyalty Program =====");
            System.out.println("1. Enroll Member");
            System.out.println("2. Add Flight Activity (Credit Miles)");
            System.out.println("3. Redeem Reward");
            System.out.println("4. View Member Statement");
            System.out.println("5. View All Members");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Enroll
                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    service.enroll(id, name, email);
                    break;

                case 2: // Flight Activity
                    System.out.print("Enter Member ID: ");
                    String memId = sc.nextLine();
                    Member m = service.findMember(memId);
                    if (m == null) {
                        System.out.println("❌ Member not found!");
                        break;
                    }
                    System.out.print("Enter Flight No: ");
                    String flightNo = sc.nextLine();
                    System.out.print("Enter Distance: ");
                    int dist = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Fare Class (Economy/Business): ");
                    String fare = sc.nextLine();

                    FlightActivity fa = new FlightActivity("FA" + System.currentTimeMillis(), flightNo, new Date(), dist, fare);
                    service.creditMiles(m, fa);
                    break;

                case 3: // Redeem
                    System.out.print("Enter Member ID: ");
                    String memId2 = sc.nextLine();
                    Member m2 = service.findMember(memId2);
                    if (m2 == null) {
                        System.out.println("❌ Member not found!");
                        break;
                    }
                    System.out.println("Available Rewards:");
                    for (Reward r : service.rewards) {
                        System.out.println(" - " + r.getName());
                    }
                    System.out.print("Enter Reward Name: ");
                    String rewardName = sc.nextLine();
                    service.redeemReward(m2, rewardName);
                    break;

                case 4: // Statement
                    System.out.print("Enter Member ID: ");
                    String memId3 = sc.nextLine();
                    Member m3 = service.findMember(memId3);
                    if (m3 != null) m3.printStatement();
                    else System.out.println("❌ Member not found!");
                    break;

                case 5: // All members
                    service.printAllStatements();
                    break;

                case 0: // Exit
                    System.out.println("👋 Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}


