class Employee {

    protected String name;
    protected String email;
    protected int experience;

    public Employee(String name, String email, int experience){
        this.name = name;
        this.email = email;
        this.experience=experience;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getExperience(){
        return experience;
    }
}

class Developer extends Employee {

    protected String mainLanguage;
    protected String[] skills;

    public Developer(String name, String email, int experience, String mainLanguage, String[] skills){
        super(name,email,experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills;
    }

    public String getMainLanguage(){
        return mainLanguage;
    }
    public String[] getSkills(){
        return skills;
    }
}

class DataAnalyst extends Employee {

    protected boolean phd;
    protected String[] methods;

    public DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd=phd;
        this.methods=methods;
    }

    public boolean isPhd(){
        return phd;
    }
    public String[] getMethods(){
        return methods;
    }
}