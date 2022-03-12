package projectA.projectA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projectA.projectA.business.FileBusiness;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.exception.UserException;
import projectA.projectA.model.Response;
import projectA.projectA.model.companyModel.CompanyUploadProFile;
import projectA.projectA.model.UploadFileReq;
import projectA.projectA.repository.CompanyRepository;
import projectA.projectA.repository.CompanyWorkRepository;
import projectA.projectA.repository.UserRepository;
import projectA.projectA.service.TokenService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/file")
public class FileController {
    public static String uploadDirectory = System.getProperty("user.dir");
    public final UserRepository userRepository;
    public final TokenService tokenService;
    public final CompanyRepository companyRepository;
    public final CompanyWorkRepository companyWorkRepository;
    public final FileBusiness fileBusiness;

    public FileController(UserRepository userRepository, TokenService tokenService, CompanyRepository companyRepository, CompanyWorkRepository companyWorkRepository, FileBusiness fileBusiness) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.companyRepository = companyRepository;
        this.companyWorkRepository = companyWorkRepository;
        this.fileBusiness = fileBusiness;
    }

    @PostMapping("/image/user-profile")
    public Object uploadProfilePicture(@RequestParam("fileName") MultipartFile file) throws IOException {

        System.out.println(uploadDirectory);
        User user = tokenService.getUserByIdToken();
        String dir = new UploadFileReq().getDirUserProfile();
        String timeStamp = new UploadFileReq().getTimeStamp();
        String imgName = new UploadFileReq().getImgName();

        //validate file
        if (file == null) {
            //throw error
            throw UserException.notFoundId();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw UserException.loginFailEmailNotFound();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw UserException.passwordOldIncorrect();
        }

        StringBuilder fileNames = new StringBuilder();

        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Object, Object> img = new HashMap<>();
        img.put("url", "http://localhost:8080" + dir + imgName);
        img.put("name", imgName);
        user.setPicture(imgName);
        userRepository.save(user);

        return new Response().ok("upload success", "img", img);

    }

    @PostMapping("/image/company-profile")
    public Object uploadCompanyProfilePicture(@RequestParam("fileName") MultipartFile file) throws IOException {

        Company companyByIdToken = tokenService.getCompanyByIdToken();
        String dir = new CompanyUploadProFile().getDir();
        String timeStamp = new CompanyUploadProFile().getTimeStamp();
        String imgName = new CompanyUploadProFile().getImgName();

        //validate file
        if (file == null) {
            //throw error
            throw UserException.notFoundId();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw UserException.loginFailEmailNotFound();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw UserException.passwordOldIncorrect();
        }

        StringBuilder fileNames = new StringBuilder();

        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Object, Object> img = new HashMap<>();
        img.put("url", "http://localhost:8080" + dir + imgName);
        img.put("name", imgName);
        companyByIdToken.setPicture(imgName);
        companyRepository.save(companyByIdToken);

        return new Response().ok("upload success", "img", img);

    }

    @PostMapping("/company-work/{id}")
    public ResponseEntity<Void> upload(@PathVariable Integer id, @RequestParam("files") MultipartFile[] files) {
        ArrayList<String> picture = new ArrayList<>();
        try {
            System.out.println("File List");
            for (MultipartFile file : files) {
                System.out.println(file.getOriginalFilename());
                save(id,file);
                picture.add(save(id,file));
            }
            System.out.println(picture);
            Optional<CompanyWork> byId = companyWorkRepository.findById(id);
            CompanyWork companyWork = byId.get();
            companyWork.setPicture(picture.toString());
            companyWorkRepository.save(companyWork);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private String save(Integer id,MultipartFile file) {
        String dir = new CompanyUploadProFile().getDir();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
            String newFileName = simpleDateFormat.format(new Date()) + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDirectory + dir, newFileName);
            Files.write(path, bytes);
            return newFileName;
        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping("/testfile/{id}")
    public ResponseEntity<Object> imageCompanyWork(@PathVariable Integer id){
        Object response = fileBusiness.imageCompanyWork(id);
        return ResponseEntity.ok(response);
    }


}
