package cn.gukeer.sync.dataDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EventData<T extends Object> {
    
    private String objectKey;
    
    private EventType event;
    
    private List<T> dataList;
    
    private Map<String,Object> params;
    
    public EventData() {}
    
    public EventData(String objectKey, EventType event, List<T> dataList) {
        this.objectKey = objectKey;
        this.event = event;
        this.dataList = dataList;
    }
    
    public EventData(String objectKey, EventType event, T data) {
    	this.objectKey = objectKey;
    	this.event = event;
    	
    	dataList = new ArrayList<T>(1);
    	dataList.add(data);
    }
    
    public EventData(String objectKey, EventType event, List<T> list, Map<String, Object> params){
	    	this.objectKey = objectKey;
	      this.event = event;
	      this.dataList = list;
	      this.params = params;
    }
    
    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

	public Map<String,Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
    
}
