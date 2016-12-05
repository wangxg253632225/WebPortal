package com.controller;

import com.jfinal.core.Controller;

/**
 * Created by lizy_java on 2016/12/5.
 */
public class FrontController extends Controller {

    public void index(){
        renderJsp("index.jsp");
    }

}
