package cn.gukeer.sync.dataDefinition;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public enum EventType {
	
    INSERT,
    
    MODIFY,
    
    DELETE;

    public static Map<String, EventType> eventTypes = new HashMap<String, EventType>();
    static{
    	eventTypes.put(EventType.INSERT.name().toLowerCase(), INSERT);
    	eventTypes.put("i", INSERT);
    	
    	eventTypes.put(EventType.MODIFY.name().toLowerCase(), MODIFY);
    	eventTypes.put("update", MODIFY);
    	eventTypes.put("u", MODIFY);
    	eventTypes.put("m", MODIFY);
    	
    	eventTypes.put(EventType.DELETE.name().toLowerCase(), DELETE);
    	eventTypes.put("remove", DELETE);
    	eventTypes.put("r", DELETE);
    	eventTypes.put("d", DELETE);
    }
    /**
     * 转换event为enum，下面是映射关系：
     * <pre>
     *    insert -> EventType.INSERT
     *    i      -> EventType.INSERT
     *    
     *    modify -> EventType.MODIFY
     *    update -> EventType.MODIFY
     *    u      -> EventType.MODIFY
     *    m      -> EventType.MODIFY
     *    
     *    delete -> EventType.DELETE
     *    remove -> EventType.DELETE
     *    r      -> EventType.DELETE
     *    d      -> EventType.DELETE
     * </pre>
     * @param event
     * @return
     */
    public static EventType toEventType(String event) {
    	if(StringUtils.isEmpty(event)) {
    		return null;
    	}
    	event = StringUtils.lowerCase(event);
    	if(eventTypes.containsKey(event)) {
    		return eventTypes.get(event);
    	}
    	return null;
    }
    
}
