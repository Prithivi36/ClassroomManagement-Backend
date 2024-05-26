package com.Classroom.Classroom.ServiceImpl;

import com.Classroom.Classroom.Entity.StudentInfo;
import com.Classroom.Classroom.Exception.APIException;
import com.Classroom.Classroom.Repository.StudentRepository;
import com.Classroom.Classroom.Service.MessengerClass;
import com.Classroom.Classroom.Service.StudentInfoService;
import com.Classroom.Classroom.dto.ListDto;
import com.Classroom.Classroom.dto.StudentDto;
import com.azure.communication.messages.NotificationMessagesClient;
import com.azure.communication.messages.NotificationMessagesClientBuilder;
import com.azure.communication.messages.models.MessageReceipt;
import com.azure.communication.messages.models.MessageTemplate;
import com.azure.communication.messages.models.SendMessageResult;
import com.azure.communication.messages.models.TemplateNotificationContent;
import com.azure.core.credential.AzureKeyCredential;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessengerClass {

    @Autowired
    StudentRepository studentRepository;

    @Value("${azure.endpoint}")
    String endpoint;

    @Value("${azure.key}")
    String azureKey;

    @Value("${azure.channelRegistrationId}")
    String azureChannelRegistrationId;

    @Override
    public String sendMessage(List<Long> numbers) {

        AzureKeyCredential azureKeyCredential= new AzureKeyCredential(azureKey);
        NotificationMessagesClient notificationClient=new NotificationMessagesClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                .buildClient();

        String channelRegistrationId=azureChannelRegistrationId;




        for(Long id : numbers){
            List<String> recipientList = new ArrayList<>();
            StudentInfo stud=studentRepository.findByRegNo(id).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"Student Not Found"));

            String parentNum="+91"+stud.getPhone();
                recipientList.add(parentNum);
            String templateName="absent_details";
            String templateLanguage = "en_us";
            MessageTemplate messageTemplate = new MessageTemplate(templateName, templateLanguage);
            TemplateNotificationContent templateContent = new TemplateNotificationContent(channelRegistrationId, recipientList, messageTemplate);
            SendMessageResult templateMessageResult = notificationClient.send(templateContent);
            for (MessageReceipt messageReceipt : templateMessageResult.getReceipts()) {
                System.out.println("Message sent to:" + messageReceipt.getTo() + " and message id:" + messageReceipt.getMessageId());
            }
        }




        return "Success";
    }
}
