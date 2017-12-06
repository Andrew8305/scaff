package com.scaff.data.analyze;

import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xyl on 11/10/17.
 */
@RestController
@Slf4j
public class AnalyzeController {

    @PostMapping("/analyze")
    public boolean analyze(@FastJson User user){
        log.info("需要分析的人是：{}",user.toString());
        return true;
    }

}
