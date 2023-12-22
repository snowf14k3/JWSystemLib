package moe.select.course;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CourseForm {

    /* 表单所需数据 */
    private Map<String, String> formData;

    public CourseForm() {
        this.formData = new LinkedHashMap<>();
    }

    public CourseForm(LinkedHashMap<String, String> newMap) {
        this.formData = newMap;
    }

    /**
     * @param sEcho  不知道
     * @param start  开始的 索引值
     * @param length 索引值+1 + length 就是显示的个数
     */
    public void putRequiredFormData(String sEcho, String start, String length) {
        /*不知道是啥玩意*/
        this.putData("sEcho", sEcho);
        /* 必修固定11列*/
        this.putData("iColumns", "11");
        this.putData("sColumns", "");
        this.putData("iDisplayStart", start);
        this.putData("iDisplayLength", length);
        /* 下面对应每列的数据 */
        this.putData("mDataProp_0", "kch");
        this.putData("mDataProp_1", "kcmc");
        this.putData("mDataProp_2", "fzmc");
        this.putData("mDataProp_3", "ktmc");
        this.putData("mDataProp_4", "xf");
        this.putData("mDataProp_5", "skls");
        this.putData("mDataProp_6", "sksj");
        this.putData("mDataProp_7", "skdd");
        this.putData("mDataProp_8", "xqmc");
        this.putData("mDataProp_9", "ctsm");
        this.putData("mDataProp_10", "czOper");
    }

    /**
     * @param sEcho  不知道
     * @param start  开始的 索引值
     * @param length 索引值+1 + length 就是显示的个数
     */
    public void putElectiveFormData(String sEcho, String start, String length) {
        /*不知道是啥玩意*/
        this.putData("sEcho", sEcho);
        /* 选修固定13列*/
        this.putData("iColumns", "13");
        this.putData("sColumns", "");
        this.putData("iDisplayStart", start);
        this.putData("iDisplayLength", length);
        /* 下面对应每列的数据 */
        this.putData("mDataProp_0", "kch");
        this.putData("mDataProp_1", "kcmc");
        this.putData("mDataProp_2", "xf");
        this.putData("mDataProp_3", "skls");
        this.putData("mDataProp_4", "sksj");
        this.putData("mDataProp_5", "skdd");
        this.putData("mDataProp_6", "xqmc");
        this.putData("mDataProp_7", "xxrs");
        this.putData("mDataProp_8", "xkrs");
        this.putData("mDataProp_9", "syrs");
        this.putData("mDataProp_10", "ctsm");
        this.putData("mDataProp_11", "szkcflmc");
        this.putData("mDataProp_12", "czOper");
    }

    /**
     * 删除全部数据
     */
    public void clear(){
        this.formData.clear();
    }


    /**
     * 覆盖旧的map
     *
     * @param newMap 新建 表单数据
     */
    public void setFormData(HashMap<String, String> newMap) {
        this.formData = newMap;
    }

    public void putData(String key, String value) {
        this.formData.put(key, value);
    }

    public Map<String, String> getFormData() {
        return formData;
    }
}
