package com.interview.spring.simpleimpl.mvc.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelAndView
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 01:38
 */
public class ModelAndView {
    /**
     * 页面路径
     */
    private String view;

    /**
     * 页面data数据
     */
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }

    public String getView() {
        return view;
    }

    public ModelAndView addObject(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this;
    }

    public ModelAndView addAllObjects(Map<String, ?> modelMap) {
        model.putAll(modelMap);
        return this;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
