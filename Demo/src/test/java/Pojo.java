import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Pojo {
    public String anomalyId;

    public int severityId;
    public String instanceId;
    public String kpiId;
    public String categoryId;
    public String kpiAttributes;
    public long anomalyTime;
    public String serviceId;
    public String kpiValues;
    public String thresholdType;
    public String operationType;
    public Map<String, Float> thresholds;
    public Map<String, String> metadata;
}