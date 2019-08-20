package com.ljq.plugin.action;

import com.ljq.plugin.template.bean.Template;
import com.ljq.plugin.utils.TemplateManager;

import java.util.ArrayList;
import java.util.List;

public class AutoKtAction extends BaseAutoCodeAction {

    @Override
    public List<Template> getActivityTemplateList(TemplateManager templateManager) {
        //kotlin act 模版
        Template templateActKt = templateManager.getTemplateActKt();
        //kotlin vm 模版
        Template templateVMKt = templateManager.getTemplateVMKt();
        //layout 模版
        Template templateLayout = templateManager.getTemplateActLayout();

        ArrayList<Template> templateList = new ArrayList<>();
        templateList.add(templateActKt);
        templateList.add(templateVMKt);
        templateList.add(templateLayout);
        return templateList;
    }

    @Override
    public List<Template> getFragmentTemplateList(TemplateManager templateManager) {
        //kotlin fragment 模版
        Template templateFragmentKt = templateManager.getTemplateFragmentKt();
        //kotlin vm 模版
        Template templateVMKt = templateManager.getTemplateVMKt();
        //fragment layout 模版
        Template templateModelKt = templateManager.getTemplateFragmentLayout();

        ArrayList<Template> templateList = new ArrayList<>();
        templateList.add(templateFragmentKt);
        templateList.add(templateVMKt);
        templateList.add(templateModelKt);
        return templateList;
    }

    @Override
    public List<Template> getDialogTemplateList(TemplateManager templateManager) {
        //kotlin dialog 模版
        Template templateDialog = templateManager.getTemplateDialogKt();
        //fragment layout 模版
        Template templateLayout = templateManager.getTemplateDialogLayout();

        ArrayList<Template> templateList = new ArrayList<>();
        templateList.add(templateDialog);
        templateList.add(templateLayout);
        return templateList;
    }


}

