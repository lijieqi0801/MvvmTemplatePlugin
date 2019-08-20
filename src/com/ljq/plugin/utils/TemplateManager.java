package com.ljq.plugin.utils;

import com.intellij.openapi.actionSystem.AnAction;
import com.ljq.plugin.template.bean.Template;

import java.io.File;
import java.io.InputStream;

public class TemplateManager {
    private AnAction action;
    private String idePath;
    private String moduleName;
    private String resourcePathName;

    public TemplateManager(AnAction action, String idePath, String moduleName, String resourcePathName) {
        this.action = action;
        this.idePath = idePath;
        this.moduleName = moduleName;
        this.resourcePathName = resourcePathName;
    }

    public Template getTemplateVMKt() {
        InputStream vmInput = action.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TVmKt.txt");
        String tContractContent = FileUtils.readTemplateFile(vmInput);
        String contractDir = idePath + "/vm";
        String contractFileName = moduleName + "VM.kt";
        return new Template(contractDir, contractFileName, tContractContent);
    }

    /**
     * Kotlin Activity 模版
     */
    public Template getTemplateActKt() {
        InputStream actInput = action.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmActivityKt.txt");
        String tActContent = FileUtils.readTemplateFile(actInput);
        String actDir = idePath + "/ui";
        String actFileName = moduleName + "Activity.kt";
        return new Template(actDir, actFileName, tActContent);
    }

    public Template getTemplateActLayout() {
        InputStream actLayoutInput = action.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmLayout.txt");
        String layoutContent = FileUtils.readTemplateFile(actLayoutInput);
        String layoutDir = resourcePathName + File.separator;
        String fileName = "activity" + StringUtil.camelToUnderline(moduleName) + ".xml";
        return new Template(layoutDir, fileName, layoutContent);
    }

    /**
     * Kotlin Fragment 模版
     */
    public Template getTemplateFragmentKt() {
        InputStream fragmentInput = this.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmFragmentKt.txt");
        String tFragmentContent = FileUtils.readTemplateFile(fragmentInput);
        String fragmentDir = idePath + "/ui/fragment";
        String fragmentFileName = moduleName + "Fragment.kt";
        return new Template(fragmentDir, fragmentFileName, tFragmentContent);
    }

    public Template getTemplateFragmentLayout() {
        InputStream actLayoutInput = action.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmLayout.txt");
        String layoutContent = FileUtils.readTemplateFile(actLayoutInput);
        String layoutDir = resourcePathName + File.separator;
        String fileName = "fragment" + StringUtil.camelToUnderline(moduleName) + ".xml";
        return new Template(layoutDir, fileName, layoutContent);
    }


    /**
     * Kotlin dialog 模版
     */
    public Template getTemplateDialogKt() {
        InputStream inputStream = this.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmDialogKt.txt");
        String content = FileUtils.readTemplateFile(inputStream);
        String dir = idePath + "/ui/dialog";
        String fileName = moduleName + "Dilaog.kt";
        return new Template(dir, fileName, content);
    }

    public Template getTemplateDialogLayout() {
        InputStream actLayoutInput = action.getClass().getResourceAsStream("/com/ljq/plugin/template/kt/TMvvmLayout.txt");
        String layoutContent = FileUtils.readTemplateFile(actLayoutInput);
        String layoutDir = resourcePathName + File.separator;
        String fileName = "dialog" + StringUtil.camelToUnderline(moduleName) + ".xml";
        return new Template(layoutDir, fileName, layoutContent);
    }

}
