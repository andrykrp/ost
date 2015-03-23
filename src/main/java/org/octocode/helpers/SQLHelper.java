package org.octocode.helpers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SQLHelper {
    public static String getSQL(ServletContext context, SQLTemplate sql, Map<String, Object> data) {
        try {
            Template template = getTemplate(context, sql);

            Writer str = new StringWriter();
            template.process(data, str);
            str.flush();

            return str.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Template getTemplate(ServletContext context, SQLTemplate sql) throws IOException {
        Configuration cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(context, "WEB-INF/templates");
        return cfg.getTemplate(sql.getName());
    }

    private static String join(List<String> list, String separator) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            buffer.append("'").append(it.next()).append("'");
            if (it.hasNext())
                buffer.append(separator);
        }
        return buffer.toString();
    }
}
