package projectA.projectA.model.userModel;


import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserUploadFile {


    private String dir = "/uploads/image/Userprofile/";
    private String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    private String imgName = timeStamp + ".png";

}