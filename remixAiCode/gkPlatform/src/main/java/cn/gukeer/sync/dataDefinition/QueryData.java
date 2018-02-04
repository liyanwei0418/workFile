package cn.gukeer.sync.dataDefinition;

import java.util.List;

public class QueryData<T extends Object> {

    private String groupOp;

    private List<QueryRules> rules;

    private int page;

    private int limit;

    public QueryData() {
    }

    public QueryData(String groupOp, List<QueryRules> rules, int page, int limit) {
        this.groupOp = groupOp;
        this.rules = rules;
        this.page = page;
        this.limit = limit;
    }

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<QueryRules> getRules() {
        return rules;
    }

    public void setRules(List<QueryRules> rules) {
        this.rules = rules;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
