package com.chenbk.boot.controller;

import com.chenbk.boot.service.UuidService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Kang on 2018/7/5.
 */
@RestController
@RequestMapping(value = "/uuid")
public class UuidController {

    @Autowired
    private UuidService uuidService;

    @ApiOperation(value = "获取UUID", notes = "获取UUID")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map uuid() {
        return Collections.singletonMap("UUID",String.valueOf(uuidService.genUUID()));
    }
}
