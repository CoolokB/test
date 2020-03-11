package com.test.tools;

import com.test.Page;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class PagesContainer {

    List<Page> mPages = new ArrayList<>();
    List<Page> pPages = new ArrayList<>();
    List<Page> caPages = new ArrayList<>();
    List<Page> cpPages = new ArrayList<>();

}
