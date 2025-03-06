package algorithm.design;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #1472 设计浏览器历史记录
 */
public class BrowserHistory {

    private int cursor;
    private List<String> history;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        cursor = 0;
    }

    /**
     * 访问给定网站，并清空前进记录
     * @param url 网站地址
     */
    public void visit(String url) {
        while (history.size() > cursor + 1) {
            history.remove(history.size() - 1);
        }
        history.add(url);
        cursor = history.size() - 1;
    }

    /**
     * 在历史记录中回退给定步数，超出则回退最大步数
     * @param steps 步数
     * @return 回退后的网站地址
     */
    public String back(int steps) {
        cursor = Math.max(0, cursor - steps);
        return history.get(cursor);
    }

    /**
     * 在历史记录中前进给定步数，超出则前进最大步数
     * @param steps 步数
     * @return 前进后的网站地址
     */
    public String forward(int steps) {
        cursor = Math.min(history.size() - 1, cursor + steps);
        return history.get(cursor);
    }
}

