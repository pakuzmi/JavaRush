package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init(){

    }

    public void exit(){
        System.exit(0);
    }

    public void resetDocument(){
        UndoListener listener = view.getUndoListener();
        if (document != null){
            document.removeUndoableEditListener(listener);
        }
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
            document.addUndoableEditListener(listener);
            view.update();
    }
}
