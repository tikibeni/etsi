package planapp.domain;

public class Plan {
    private String planName;
    private String userName;
    
    public Plan(String planName, String userName) {
        this.planName = planName;
        this.userName = userName;
    }
    
    public String getPlanName() {
        return this.planName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plan)) {
            return false;
        }
        
        Plan p = (Plan) obj;
        return planName.equals(p.planName);
    }
    
    @Override
    public String toString() {
        return "Plan: " + this.planName + ", by:" + this.userName;
    }
}
