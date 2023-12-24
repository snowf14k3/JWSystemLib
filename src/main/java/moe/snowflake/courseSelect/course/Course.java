package moe.snowflake.courseSelect.course;

public class Course {
    private String kcid;
    private String jxID;

    private String area;

    private String teacher;

    private String score;
    private String type;
    private String name;

    private int remain;

    public Course(){
    }

    /**
     * 自行创建使用
     * @param kcid 课程ID
     * @param jxID jxID
     */
    public Course(String kcid, String jxID) {
        this.kcid = kcid;
        this.jxID = jxID;
    }

    /**
     *
     * <p>
     * 参数默认值
     * <p>
     * trjf:        投入积分
     * <p>
     * xkzy:        选课志愿
     * <p>
     * cfbs: null
     * <p>
     * @param kcid 课程ID jx02id
     * @param jxID jxID  jx0404id
     * @param teacher 老师 skls
     */
    public Course(String kcid, String jxID, String teacher) {
        this.kcid = kcid;
        this.jxID = jxID;
        this.teacher = teacher;
    }


    public void setKCID(String KCID) {
        this.kcid = KCID;
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
        this.remain = Integer.parseInt(remain);
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

    public int getRemain() {
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
