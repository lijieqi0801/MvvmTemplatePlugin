package com.ljq.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.ljq.plugin.TemplateEnum;
import com.ljq.plugin.template.bean.Template;
import com.ljq.plugin.ui.CreateMvvmDialog;
import com.ljq.plugin.utils.DateUtils;
import com.ljq.plugin.utils.FileUtils;
import com.ljq.plugin.utils.PackageManager;
import com.ljq.plugin.utils.TemplateManager;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class BaseAutoCodeAction extends AnAction {
    private Project project;
    private String moduleName;
    private String idePath;
    private String pathPackageName;
    private String resourcePathName;


    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        project = event.getData(PlatformDataKeys.PROJECT);
        idePath = FileUtils.getCursorPath(event);
        resourcePathName=PackageManager.getResourcePathName(idePath);
        pathPackageName = PackageManager.getPathPackageName(idePath);
        if (TextUtils.isEmpty(pathPackageName)) {
            Messages.showMessageDialog(project, "dir is error!", "Error", Messages.getErrorIcon());
            return;
        }
        showDialog();
    }

    private void showDialog() {
        CreateMvvmDialog dialog = new CreateMvvmDialog();
        dialog.setCallBackListener(this::handleTemplateOption);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void handleTemplateOption(String name, TemplateEnum type) {
        moduleName = name;
        TemplateManager templateManager = new TemplateManager(this, idePath, moduleName,resourcePathName);
        List<Template> templateList = null;
        switch (type) {
            case Act:
                templateList = getActivityTemplateList(templateManager);
                break;
            case Fragment:
                templateList = getFragmentTemplateList(templateManager);
                break;
            case Dialog:
                templateList = getDialogTemplateList(templateManager);
                break;
        }
        if (templateList == null) {
            Messages.showMessageDialog(project, "not found template!", "Error", Messages.getErrorIcon());
        } else {
            createTemplateList(type, templateList);
        }
    }

    /**
     * 遍历模版列表并创建文件
     */
    private void createTemplateList(TemplateEnum type, List<Template> templateList) {
        if (!checkFileExists(templateList)) return;
        for (Template template : templateList) {
            createTemplateFile(template);
        }
        Messages.showMessageDialog(project, moduleName + type.value + " create success!", "Information", Messages.getInformationIcon());
    }

    private boolean checkFileExists(List<Template> templateList) {
        for (Template template : templateList) {
            if (template.getFile().exists()) {
                Messages.showMessageDialog(project, template.getFile().getName() + " file already exists!", "Error", Messages.getErrorIcon());
                return false;
            }
        }
        return true;
    }

    private void createTemplateFile(Template template) {
        String fillContent = FileUtils.fillTemplateContent(template.getContent(), moduleName, pathPackageName, DateUtils.getCurDate());
        FileUtils.writeToFile(fillContent, template.getDir(), template.getFileName());
    }

    public abstract List<Template> getActivityTemplateList(TemplateManager templateManager);

    public abstract List<Template> getFragmentTemplateList(TemplateManager templateManager);

    public abstract List<Template> getDialogTemplateList(TemplateManager templateManager);
}
