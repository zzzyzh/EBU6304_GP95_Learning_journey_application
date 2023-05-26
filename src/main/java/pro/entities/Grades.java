package pro.entities;

import pro.utils.Convertor;

/**
 * A class to represent a course with specific name, term, grade, credit, rank and gpa
 * @author Yixuan Gou
 */
public class Grades extends Convertor {

    private String name;        
    private int term;           
    private double grade;       
    private double credit;      
    private int rank;

    /**
     * Constructs a specific course with specific name, term, mark, credit, rank
     * 
     * @param name       course name
     * @param term       term of the course
     * @param grade      mark got
     * @param credit     course credit
     * @param rank       rank of the mark
     */
    public Grades(String name, int term, double grade, double credit, int rank) {
        this.setName(name);
        this.setTerm(term);
        this.setGrade(grade);
        this.setCredit(credit);
        this.setRank(rank);
    }

    /**
     * Constructs a default course
     */
    public Grades() {}

    /**
     * Get the course name
     * 
     * @return course name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the course name 
     * 
     * @param name course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets term of course
     * 
     * @return course term
     */
    public int getTerm() {
        return this.term;
    }

    /**
     * Sets the term
     * 
     * @param term course term
     */
    public void setTerm(int term) {
        this.term = term;
    }
    
    /**
     * Gets course grade
     * 
     * @return course grade
     */
    public double getGrade() {
        return this.grade;
    }

    /**
     * Sets course grade
     * 
     * @param grade course grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    /**
     * Gets course credit
     * 
     * @return course credit
     */
    public double getCredit() {
        return this.credit;
    }
    /**
     * Sets course credit
     * 
     * @param credit course credit
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * Gets grade rank
     * 
     * @return grade rank
     */
    public int getRank() {
        return this.rank;
    }
    /**
     * Sets grade rank
     * 
     * @param rank grade rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Gets GPA
     * 
     * @return value of GPA
     */
    public double getGpa() {
        return grade < 60? 0 : (4 - 3 * (100 - grade) * (100 - grade) / 1600);
    }

    /**
     * Grades a equals to grades b only if a.name == b.name
     * 
     * @return if both course have the same name
     */
    public boolean equals(Grades grades) {
        return this.name.equals(grades.getName());
    }
}
