package com.tramp.idea.enums;

/**
 * Spring mvc 注解包路径
 *
 * @author chengsheng@qbb6.com
 * @date 2019/2/11 3:57 PM
 */
public enum SpringMVCEnum {

    REQUESTMAPPING("org.springframework.web.bind.annotation.RequestMapping"),
    GETMAPPING("org.springframework.web.bind.annotation.GetMapping"),
    POSTMAPPING("org.springframework.web.bind.annotation.PostMapping"),
    PUTMAPPING("org.springframework.web.bind.annotation.PutMapping"),
    DELETEMAPPING("org.springframework.web.bind.annotation.DeleteMapping"),
    PATCHMAPPING("org.springframework.web.bind.annotation.PatchMapping"),
    REQUESTBODY("org.springframework.web.bind.annotation.RequestBody"),
    REQUESTPARAM("org.springframework.web.bind.annotation.RequestParam"),
    REQUESTHEADER("org.springframework.web.bind.annotation.RequestHeader"),
    REQUESTATTRIBUTE("org.springframework.web.bind.annotation.RequestAttribute"),
    PATHVARIABLE("org.springframework.web.bind.annotation.PathVariable");

    private String value;

    SpringMVCEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
