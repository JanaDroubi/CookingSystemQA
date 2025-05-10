package all;

public class CustomerProfile extends Person {

    private String dietaryPreference;
    private String allergy;

    public CustomerProfile(String userName, String pass, String role, String dietaryPreference, String allergy) {
        super(userName, pass, role);
        this.dietaryPreference = dietaryPreference;
        this.allergy = allergy;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public String getAllergy() {
        return allergy;
    }

    public boolean isValid() {
        return userName != null && dietaryPreference != null && allergy != null;
    }

    public boolean isMealValid() {
        return !dietaryPreference.toLowerCase().contains(allergy.toLowerCase());
    }
}
