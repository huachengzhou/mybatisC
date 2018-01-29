package cn.mybatis.common;

public enum DaoEnum {
    DAO_ENUM("test.");
    private String var;

    DaoEnum(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
