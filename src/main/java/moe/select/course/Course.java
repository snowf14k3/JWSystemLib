package moe.select.course;

public class Course {
    private String kcid;
    private String jxID;

    private String area;

    private String teacher;

    private String score;
    private String type;
    private String name;

    private String remain;

    public Course(){
    }

    /**
     * =================
     * 参数默认值
     * trjf:        投入积分
     * xkzy:        选课志愿
     * cfbs: null
     * =================
     *
     * @param kcid 课程ID jx02id
     * @param jxID jxID  jx0404id
     * @param tearcher 老师 skls
     */
    public Course(String kcid, String jxID, String tearcher) {
        this.kcid = kcid;
        this.jxID = jxID;
        this.teacher = tearcher;
    }

    public void setKCID(String kcid) {
        this.kcid = kcid;
    }

    public void setJxID(String jxID) {
        this.jxID = jxID;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getKcid() {
        return kcid;
    }

    public String getJxID() {
        return jxID;
    }

    public String getArea() {
        return area;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getRemain() {
        return remain;
    }

    @Override
    public String toString() {
        return "Course{" +
                "kcid='" + kcid + '\'' +
                ", jxID='" + jxID + '\'' +
                ", area='" + area + '\'' +
                ", teacher='" + teacher + '\'' +
                ", score='" + score + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", remain='" + remain + '\'' +
                '}';
    }
}
