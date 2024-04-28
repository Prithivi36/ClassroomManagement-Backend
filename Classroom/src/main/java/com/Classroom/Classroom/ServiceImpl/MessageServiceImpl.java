package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Service.MessengerClass;
import com.azure.communication.messages.NotificationMessagesClient;
import com.azure.communication.messages.NotificationMessagesClientBuilder;
import com.azure.communication.messages.models.MessageReceipt;
import com.azure.communication.messages.models.MessageTemplate;
import com.azure.communication.messages.models.SendMessageResult;
import com.azure.communication.messages.models.TemplateNotificationContent;
import com.azure.core.credential.AzureKeyCredential;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessengerClass {



    @Override
    public String sendMessage(String message) {

        String endpoint="https://vcr-notice.unitedstates.communication.azure.com";
        AzureKeyCredential azureKeyCredential= new AzureKeyCredential("buBYgGgHf4/pAsCQ14ArupIeVFwOeUoFoXNGanrfFnNSlm5Z3JNWsRMhGSHWu9VxTdIzZ1dpcq2cXq7DSi64Zg==");
        NotificationMessagesClient notificationClient=new NotificationMessagesClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                .buildClient();

        String channelRegistrationId="176387b9-85d1-43b6-be10-ae705e1085ab";

        List<String> recipientList = new ArrayList<>();
        recipientList.add("+919952518670");

        String templateName="absent_details";
        String templateLanguage = "en_us";
        MessageTemplate messageTemplate = new MessageTemplate(templateName, templateLanguage);
        TemplateNotificationContent templateContent = new TemplateNotificationContent(channelRegistrationId, recipientList, messageTemplate);
        SendMessageResult templateMessageResult = notificationClient.send(templateContent);
        for (MessageReceipt messageReceipt : templateMessageResult.getReceipts()) {
            System.out.println("Message sent to:" + messageReceipt.getTo() + " and message id:" + messageReceipt.getMessageId());
        }

        return "Success";
    }
}
