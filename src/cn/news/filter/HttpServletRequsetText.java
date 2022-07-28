package cn.news.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**抽象类  适配器  包装器Wrapper
 * @author LCB
 * @date 2022/7/7 12:01
 */
public class HttpServletRequsetText extends HttpServletRequestWrapper {
    private Map<String,String> textMap = null;

    public HttpServletRequsetText(HttpServletRequest request,Map map) {
        super(request);
        this.textMap = map;
    }

    @Override
    public String getParameter(String name) {
        String oldValue = super.getParameter(name);
        if (oldValue != null){
            Set<String> keys = textMap.keySet();
            Iterator<String> it = keys.iterator();
            while(it.hasNext()){
                String key = it.next(); // 脏文
                String value = textMap.get(key);

            }
        }
        return super.getParameter(name);
    }
}


