package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.model.Response;
import projectA.projectA.model.UploadFileReq;
import projectA.projectA.repository.CompanyWorkRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FileBusiness {
    UploadFileReq url = new UploadFileReq();

    private final CompanyWorkRepository companyWorkRepository;

    public FileBusiness(CompanyWorkRepository companyWorkRepository) {
        this.companyWorkRepository = companyWorkRepository;
    }

    public Object imageCompanyWork(Integer id) {
        Optional<CompanyWork> byId = companyWorkRepository.findById(id);
        CompanyWork companyWork = byId.get();
        String substring = companyWork.getPicture().substring(1, companyWork.getPicture().length() - 1);
        String[] split = substring.split(", ");
        ArrayList<String> picture = new ArrayList<>();
        for (int i = 0; i < split.length; i++){
           picture.add(url.getHost() + url.getDirCompanyProfile() + split[i]);
        }
        String image = picture.toString().substring(1, picture.toString().length() - 1);
        String[] split1 = image.split(", ");
        return new Response().ok("image", "companyWork", split1);
    }
}
