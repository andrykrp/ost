package org.octocode.helpers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerHelper {
    public static void foo (ServletContext context) {
        Configuration cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(context, "WEB-INF/templates");
        try {
            //Load template from source folder
            Template template = cfg.getTemplate("tmpl.sql");

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //List parsing
            List<String> countries = new ArrayList<String>();
            countries.add("India");
            countries.add("United States");
            countries.add("Germany");
            countries.add("France");

            data.put("countries", countries);


            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();

            // File output
            Writer file = new FileWriter(new File("C:\\temp\\FTL_helloworld.txt"));
            template.process(data, file);
            file.flush();
            file.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
