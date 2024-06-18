package moe.snowflake.jwSystem.utils;

import com.google.gson.*;
import moe.snowflake.jwSystem.course.Course;

import java.util.ArrayList;

/**
 *
 * BACKUP DATA
 *
 *
 */
public class CourseDataHandler {

    // 新建json
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * 获取课程的全部个数
     * @param json 服务器返回json数据
     * @return
     */
    @Deprecated()
    public static String getTotalRecords(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        return jsonObject.get("iTotalRecords").getAsString();
    }

    /**
     *
     * @param json 服务器返回json数据
     * @return 实例化为course列表
     */
    public static ArrayList<Course> getCourses(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        ArrayList<Course> list = new ArrayList<>();

        if (!jsonObject.has("aaData")) {
            return list;
        }

        JsonArray jsonArray = jsonObject.get("aaData").getAsJsonArray();
        for (JsonElement jo : jsonArray.asList()) {
            JsonObject object = jo.getAsJsonObject();

            Course course = new Course();
            // KCID
            course.setKCID(object.get("jx02id").getAsString());
            // KCID2
            course.setJxID(object.get("jx0404id").getAsString());
            // 上课校区
            course.setArea(object.get("xqmc").getAsString());
            // 课程名
            course.setName(object.get("kcmc").getAsString());
            // 老师
            course.setTeacher(object.get("skls").getAsString());

            JsonElement remain = object.get("syrs");
            if (remain.isJsonNull()) {
                course.setRemain("9999");
            } else {
                course.setRemain(remain.getAsString());
            }
            // 剩余人数

            // 学分
            course.setScore(object.get("xf").getAsString());
            // 类型
            JsonElement type = object.get("szkcflmc");
            if (type.isJsonNull()) {
                course.setType("Required");
                course.setRequiredCourse(true);
            } else {
                course.setType(type.getAsString());
                course.setRequiredCourse(false);
            }
            // 添加进list里
            list.add(course);
        }
        return list;
    }


}
