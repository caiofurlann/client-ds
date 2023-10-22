package caiofurlan.clientdistributedsystems.models;

import caiofurlan.clientdistributedsystems.views.ViewFactory;

public class Model {
    private static Model model = null;
    private final ViewFactory viewFactory;

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
