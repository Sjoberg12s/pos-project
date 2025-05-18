package se.kth.iv1350.pos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for showing error messages to 
 * the user.
 */
public class ErrorMessageHandler {

    /**
     * Prints out the error message to the user view.
     * @param msg the error message to be shown.
     */
    void showErrorMessage(String msg){
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    private String createTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.
            ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
