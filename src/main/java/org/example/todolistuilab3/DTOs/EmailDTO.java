package org.example.todolistuilab3.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class EmailDTO {

    private String recipient;
    private String subject;
    private String body;

    public EmailDTO() {
    }

    public EmailDTO (String recipientl, String subject, String body) {
        this.recipient = recipientl;
        this.subject = subject;
        this.body = body;
    }


}
