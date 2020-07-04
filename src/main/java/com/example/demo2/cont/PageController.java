package com.example.demo2.cont;

import com.example.demo2.FileStorageProperties;
import com.example.demo2.pojos.Suppliers;
import com.example.demo2.repos.SupRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class PageController {

    @Autowired
    SupRepo suppliersRepo;

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/suppliers/{id}")
    public Optional<Suppliers> fingByIdSup(@PathVariable Long id){
        return suppliersRepo.findById(id);
    }

    @RequestMapping(value = "supplier", method = RequestMethod.GET)
    public Page<Suppliers> getSupplierPage(@RequestParam("page") int page, @RequestParam("size") int size){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page, size, sort);
        return suppliersRepo.findAll(pageable);
    }

    FileStorageProperties fileStorageProperties;

    @RequestMapping(value="/uploadImage2/{filename:.+}",method = RequestMethod.POST)
    public @ResponseBody String uploadImage2(@RequestParam("imageValue") String imageValue, HttpServletRequest request, @PathVariable String filename)
    {
        try
        {
            byte[] imageByte= Base64.decodeBase64(imageValue.split(",")[1]);
            String p = "/Users/filedir/uploads/" + filename + ".jpg"; // здесь возможно сплит по точке ибо емаил
//            String directory = servletContext.getRealPath("/")+"images/sample.jpg";
            new FileOutputStream(p).write(imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }

    @PostMapping("/upload")
//    @RequestMapping(value = "/file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {

        // local path
        String p = "/Users/filedir/uploads";

        System.out.println(Paths.get(p).toAbsolutePath().normalize());
        Path fileStorageLocation = Paths.get(p).toAbsolutePath().normalize();
        Files.createDirectories(fileStorageLocation);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return "redirect:/";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

        Resource imgFile = resourceLoader.getResource("file:E:\\Users\\filedir\\uploads\\" + filename);

        // TODO set content type depending on file type
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imgFile.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(imgFile);
    }

    @RequestMapping(value = "/filesss/{filename:.+}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {

        Resource imgFile = resourceLoader.getResource("file:E:\\Users\\filedir\\uploads\\378_v1.png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/files/delete/{filename:.+}")
    public String deleteFile(@PathVariable String filename) throws IOException {
        File file = new File("E:\\Users\\filedir\\uploads\\" + filename);
        file.delete();
        // TODO можем удалять файлы из связи в бд, но в файловой системе и в таблице файлов они остаются.
        return "ok";
    }


}
