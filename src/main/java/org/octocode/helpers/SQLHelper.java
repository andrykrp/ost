package org.octocode.helpers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SQLHelper {
    public static String getSQL(ServletContext context, SQLTemplate sqlt, List<String> tags, List<String> groups, List<String> fields) {
        Configuration cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(context, "WEB-INF/templates");
        try {
            Template template = cfg.getTemplate(sqlt.getName());

            Map<String, Object> data = new HashMap<String, Object>();

            data.put("tags", join(tags, ","));
            data.put("tags_count", tags.size());
            data.put("groups", groups);
            data.put("fields", fields);

            Writer str = new StringWriter();
            template.process(data, str);
            str.flush();

            return str.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
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
