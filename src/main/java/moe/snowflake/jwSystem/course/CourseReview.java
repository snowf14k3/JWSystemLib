package moe.snowflake.jwSystem.course;

public class CourseReview {
    private final int index ;
    private final String term;

    private final String type;

    private final String reviewBatch;

    private final String start;
    private final String end;

    private final String link;

    public CourseReview(int index, String term, String type, String reviewBatch, String start, String end, String link) {
        this.index = index;
        this.term = term;
        this.type = type;
        this.reviewBatch = reviewBatch;
        this.start = start;
        this.end = end;
        this.link = link;
    }


    public int getIndex() {
        return index;
    }

    public String getTerm() {
        return term;
    }

    public String getType() {
        return type;
    }

    public String getReviewBatch() {
        return reviewBatch;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "CourseReview{" +
                "序号=" + index +
                ", 学年学期='" + term + '\'' +
                ", 评价类型='" + type + '\'' +
                ", 评价批次='" + reviewBatch + '\'' +
                ", 开始时间='" + start + '\'' +
                ", 结束时间='" + end + '\'' +
                ", 评价链接='" + link + '\'' +
                '}';
    }
}
