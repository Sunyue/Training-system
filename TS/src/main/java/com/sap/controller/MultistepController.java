package com.sap.controller;

import org.springframework.ui.Model;

public abstract class MultistepController {
    abstract String getChain(Model model, int start, int limit);
    abstract String getCourse(Model model, Integer chainId, int start, int limit);
    abstract String getMaterial(Model model, Integer courseId);
}
