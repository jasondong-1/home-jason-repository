package com.ideal.tagportrait.web.controller;

/**
 * Created by xushanshan on 15-6-19.
 */
public class BaseController extends UIController {
    public final static String PORTAL_PREFIX = "/";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
