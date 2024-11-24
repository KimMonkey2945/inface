package hello.toastuitest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@Slf4j
public class ToastUiController {

    @GetMapping("/toastUi")
    public String getToastUi(){
        return "toastUi";
    }

    @PostMapping("/upload/img")
    @ResponseBody
    public Map<String, String> uploadImage(ToastVO file) throws IOException {
        String uploadDir ="C:/Users/hvyon/Desktop/img/";
        String fileName = UUID.randomUUID().toString().replace("-", "") + file.getFile().getOriginalFilename();
        File uploadDirFile = new File(uploadDir + fileName);
        file.getFile().transferTo(uploadDirFile);

        String filePath = "/uploads/" + fileName;

        Map<String, String> map = new HashMap<>();
        map.put("url", filePath);
        return map;
    }

    @PostMapping("/cancel/img")
    @ResponseBody
    public void cancelImage(@RequestBody Map<String, List<String>> request) {
        List<String> imgUrls = request.get("files");  // 클라이언트에서 전송된 이미지 URL 목록

        String uploadDir = "C:/Users/hvyon/Desktop/img/";

        for (String imgUrl : imgUrls) {
            String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);

            File fileToDelete = new File(uploadDir + fileName);  // 파일 경로 설정

            if (fileToDelete.exists()) {
                fileToDelete.delete();  // 파일 삭제
            }
        }
    }

    @GetMapping("/getTest")
    public String insertToastUi(ToastVO param){
        log.debug("넘어온값들 : {}", param.toString());
        return "redirect://toastUi";
    }

}
