import com.appnomic.appsone.common.protbuf.A1EventProtos;
import com.appnomic.appsone.common.protbuf.CommandResponseProtos;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.ByteArrayOutputStream;

public class CollationMessageToQueue {

    public static void main(String[] args) {
       /* Map<String, String> metadata = new HashMap<>();*/
        /*metadata.put("AccountId", "heal_health");
        metadata.put("KPI_VIOLATION_TIME", "2022-11-21 06:42:00");
        metadata.put("anomalyScore", "1.0");
        metadata.put("isInformatic", "0");
        metadata.put("isGroupKpi", "false");
        metadata.put("agentUid","946ccffd-6ee8-46f4-8148-206740c40592");
        metadata.put("attributeName","ALL");
        metadata.put("isMaintenanceExcluded","0");
        metadata.put("violationLevel","SERVICE");
        metadata.put("agentIdentifier","946ccffd-6ee8-46f4-8148-206740c40592");
        metadata.put("serviceIdentifier","7d98791c-0dfc-46bd-a975-95360d557a97");
        metadata.put("kpiType","Core");
        metadata.put("suppression","1");
        metadata.put("persistence","1");
        metadata.put("starttime","1669012920000");
        metadata.put("thresholdseverity","Default");
        metadata.put("expectedAnomalyClosingTime","1669013230442");*/

        /*Pojo pojo  = Pojo.builder()
                .severityId(1)
                .instanceId("1")
                .kpiValues("10")
                .build();*/

       /* NotificationProtos.Attachment attachment = NotificationProtos.Attachment.newBuilder()
                .setContent("decompressedOutput")
                .setName("Forensic")
                .setContentType("text/html")
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("test","abc");
        List<NotificationProtos.UserDetails> list = new ArrayList<>();*/

        CommandResponseProtos.CommandResponse commandResponse = CommandResponseProtos.CommandResponse.newBuilder()
                .setAgentIdentifier("Agent")
                .setCommand("Abc")
                .setEventType("A1")
                .setCmdOut("OUT")
                .setAgentType("Component Agent")
                .setCommandJobId("ID")
                .setCommandStartTime(345)
                .setCommandCompleteTime(1234)
                .setExitCode(1)
                .setStdErr("cvb")
                .setSupervisorIdentifier("Supervisor")
                .setTriggerSource("Trigger")
                .build();


        A1EventProtos.A1Event a1Event = A1EventProtos.A1Event.newBuilder()
                .setEventType("NOTIFICATION_OUTPUT")
                .setEventData(commandResponse.toByteString())
                .build();

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.13.169");
            factory.setPort(5672);
            factory.setVirtualHost("/");
            factory.useSslProtocol();
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare("raw-forensic-messages", true, false, false, null);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            a1Event.writeTo(os);
            channel.basicPublish("", "raw-forensic-messages", null, os.toByteArray());
            System.out.println("Data Inserted");

            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}