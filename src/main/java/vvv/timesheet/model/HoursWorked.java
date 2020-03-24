package vvv.timesheet.model;

import javax.persistence.*;

@Entity
@Table(name = "hoursworked")
public class HoursWorked {

    @Id
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @Column(name = "period")
    private String period;

    @Column(name="day1")
    private String day1;

    @Column(name="day2")
    private String day2;

    @Column(name="day3")
    private String day3;

    @Column(name="day4")
    private String day4;

    @Column(name="day5")
    private String day5;

    @Column(name="day6")
    private String day6;

    @Column(name="day7")
    private String day7;

    @Column(name="day8")
    private String day8;

    @Column(name="day9")
    private String day9;

    @Column(name="day10")
    private String day10;

    @Column(name="day11")
    private String day11;

    @Column(name="day12")
    private String day12;

    @Column(name="day13")
    private String day13;

    @Column(name="day14")
    private String day14;

    @Column(name="day15")
    private String day15;

    @Column(name="day16")
    private String day16;

    @Column(name="day17")
    private String day17;

    @Column(name="day18")
    private String day18;

    @Column(name="day19")
    private String day19;

    @Column(name="day20")
    private String day20;

    @Column(name="day21")
    private String day21;

    @Column(name="day22")
    private String day22;

    @Column(name="day23")
    private String day23;

    @Column(name="day24")
    private String day24;

    @Column(name="day25")
    private String day25;

    @Column(name="day26")
    private String day26;

    @Column(name="day27")
    private String day27;

    @Column(name="day28")
    private String day28;

    @Column(name="day29")
    private String day29;

    @Column(name="day30")
    private String day30;

    @Column(name="day31")
    private String day31;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPeriod() {
        return period;
    }

    public String getDay1() {
        return day1;
    }

    public String getDay2() {
        return day2;
    }

    public String getDay3() {
        return day3;
    }

    public String getDay4() {
        return day4;
    }

    public String getDay5() {
        return day5;
    }

    public String getDay6() {
        return day6;
    }

    public String getDay7() {
        return day7;
    }

    public String getDay8() {
        return day8;
    }

    public String getDay9() {
        return day9;
    }

    public String getDay10() {
        return day10;
    }

    public String getDay11() {
        return day11;
    }

    public String getDay12() {
        return day12;
    }

    public String getDay13() {
        return day13;
    }

    public String getDay14() {
        return day14;
    }

    public String getDay15() {
        return day15;
    }

    public String getDay16() {
        return day16;
    }

    public String getDay17() {
        return day17;
    }

    public String getDay18() {
        return day18;
    }

    public String getDay19() {
        return day19;
    }

    public String getDay20() {
        return day20;
    }

    public String getDay21() {
        return day21;
    }

    public String getDay22() {
        return day22;
    }

    public String getDay23() {
        return day23;
    }

    public String getDay24() {
        return day24;
    }

    public String getDay25() {
        return day25;
    }

    public String getDay26() {
        return day26;
    }

    public String getDay27() {
        return day27;
    }

    public String getDay28() {
        return day28;
    }

    public String getDay29() {
        return day29;
    }

    public String getDay30() {
        return day30;
    }

    public String getDay31() {
        return day31;
    }

}
