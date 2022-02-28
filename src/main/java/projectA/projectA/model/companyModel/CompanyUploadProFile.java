package projectA.projectA.model.companyModel;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CompanyUploadProFile {

    private String dir = "/uploads/image/CompanyProfile/";
    private String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    private String imgName = timeStamp + ".png";
}
