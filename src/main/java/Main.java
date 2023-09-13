import java.util.*;

public class Main {

    private static final Map<String, List<List<String>>> method = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < q; i++) {
            int op = Integer.parseInt(sc.nextLine());
            String s = sc.nextLine();
            String res;
            if (op == 1) {
                res = checkDefine(s);
            } else {
                res = checkCall(s);
            }
            System.out.println(res);
        }
    }

    private static String checkCall(String s) {
        String res = "ok.";
        int idx = s.indexOf("(");
        String name = s.substring(0, idx);
        String[] params = s.substring(idx + 1, s.length() - 1).split(",");
        if (method.containsKey(name)) {
            boolean compare = true;
            for (List<String> info : method.get(name)) {
                compare = params.length == info.size() - 1;
                if (!compare) {
                    break;
                }
                for (int i = 0; i < params.length; i++) {
                    if (!params[i].equals(info.get(i + 1))) {
                        compare = false;
                        break;
                    }
                }
                if (!compare) {
                    break;
                }
            }
            if (!compare) {
                res = "method " + name + " cannot be applied to given types";
            }
        } else {
            res = "cannot find symbol " + name + ".";
        }
        return res;
    }

    private static String checkDefine(String s) {
        String res = "ok.";
        String[] split = s.split(" ", 2);
        String retType = split[0];
        String s1 = split[1];
        int idx = s1.indexOf('(');
        String name = s1.substring(0, idx);
        String[] params = s1.substring(idx + 1, s1.length() - 1).split(",");
        for (int i = 0; i < params.length; i++) {
            String p = params[i];
            params[i] = p.split(" ")[0];
        }
        List<String> info = new ArrayList<>();
        info.add(retType);
        Collections.addAll(info, params);
        if (!method.containsKey(name)) {
            List<List<String>> list = new ArrayList<>();
            list.add(info);
            method.put(name, list);
        } else {
            List<List<String>> list = method.get(name);
            boolean compare = true;
            for (List<String> e : list) {
                compare = params.length == e.size() - 1;
                if (!compare) {
                    break;
                }
                for (int i = 0; i < params.length; i++) {
                    if (!params[i].equals(e.get(i + 1))) {
                        compare = false;
                        break;
                    }
                }
                if (!compare) {
                    break;
                }
            }
            if (compare) {
                res = "method " + name + " is already defined.";
            } else {
                list.add(info);
                method.put(name, list);
            }
        }
        return res;
    }
}