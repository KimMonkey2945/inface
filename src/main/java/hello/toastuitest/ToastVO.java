package hello.toastuitest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ToastVO {

    String text;
    List<MultipartFile> files;
    MultipartFile file;
}
