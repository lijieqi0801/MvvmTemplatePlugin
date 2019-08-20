package com.ljq.plugin.utils;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiDirectory;

import java.io.*;

public class FileUtils {
    public static void writeToFile(String content, String dir, String className) {
        File floder = new File(dir);
        if (!floder.exists()) {
            floder.mkdirs();
        }
        try {
            File file = new File(dir + "/" + className);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fillTemplateContent(String content, String moduleName, String packageName, String date) {
        if (content.contains("$moduleName_")) {
            content = content.replace("$moduleName_", moduleName);
        }
        if (content.contains("$packageName_")) {
            content = content.replace("$packageName_", packageName);
        }
        if (content.contains("$author_")) {
            content = content.replace("$author_", "ljq");
        }
        if (content.contains("$date_")) {
            content = content.replace("$date_", date);
        }
        if (content.contains("$des_")) {
            content = content.replace("$des_", "input description");
        }
        if (content.contains("$actLayout")){
            content=content.replace("$actLayout","R.layout.activity"+ StringUtil.camelToUnderline(moduleName));
        }
        if (content.contains("$fragmentLayout")){
            content=content.replace("$fragmentLayout","R.layout.fragment"+ StringUtil.camelToUnderline(moduleName));
        }
        if (content.contains("$dialogLayout")){
            content=content.replace("$dialogLayout","R.layout.dialog"+ StringUtil.camelToUnderline(moduleName));
        }
        return content;
    }


    public static String readTemplateFile(InputStream inputStream) {
        String content = "";
        try {
            content = new String(readStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    private static byte[] readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            inputStream.close();
        }
        return outputStream.toByteArray();
    }

    /**
     * 当前鼠标光标所在位置
     */
    public static String getCursorPath(AnActionEvent event) {
        String cursorPath = "";
        IdeView ideView = event.getRequiredData(LangDataKeys.IDE_VIEW);
        PsiDirectory directory = ideView.getOrChooseDirectory();
        if (directory != null) {
            cursorPath = directory.toString();
            if (cursorPath.startsWith("PsiDirectory:")) {
                cursorPath = cursorPath.substring("PsiDirectory:".length());
            }
        }
        return cursorPath;
    }
}
