package com.sap.controller;

import org.springframework.ui.Model;

public abstract class MultistepController {
    abstract String getChain(Model model);
    abstract String getCourse(Model model, Integer chainId);
    abstract String getMaterial(Model model, Integer courseId);
}
