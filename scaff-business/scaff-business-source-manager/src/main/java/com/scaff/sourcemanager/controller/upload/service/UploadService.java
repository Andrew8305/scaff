package com.scaff.sourcemanager.controller.upload.service;

import com.scaff.model.User;

/**
 * Created by xyl on 11/10/17.
 */
public interface UploadService {
    boolean dataAnalyze(User user);
    boolean dataAnalyzeFallBack(User user);
}
