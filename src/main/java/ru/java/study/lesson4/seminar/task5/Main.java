package ru.java.study.lesson4.seminar.task5;

import java.util.*;

public class Main {
    /**
     * <a href="https://leetcode.com/problems/simplify-path/">Simplify Path</a>
     */
    public static void main(String[] args) {
        String path1 = "/home/";
        System.out.println(simplifyPathTry4(path1));
        String path2 = "/../";
        System.out.println(simplifyPathTry4(path2));
        String path3 = "/home//foo/";
        System.out.println(simplifyPathTry4(path3));
        String path4 = "/a//b////c/d//././/.."; // -> "/a/b/c"
        System.out.println(simplifyPathTry4(path4));
    }

    public static String simplifyPath(String path) {
        Stack<String> pathElements = new Stack<>();
        //todo maybe there is a way to simplify this(yes if/else use less RAM)
        for (String element : path.split("/")) {
            switch (element) {
                case "":
                case ".": break;
                case "..": {
                    if (!pathElements.empty()) pathElements.remove(0);
                    break;
                }
                default: pathElements.add(element); break;
            }
        }
        StringBuilder result = new StringBuilder();
        while (!pathElements.empty()) {
            result.insert(0, pathElements.pop());
            if (pathElements.size() > 0) result.insert(0, "/");
        }
        result.insert(0, "/");
        return result.toString();
    }

    public static String simplifyPathTry2(String path) {
        ArrayDeque<String> pathElements = new ArrayDeque<>();
        for (String element : path.split("/")) {
            switch (element) {
                case "":
                case ".": break;
                case "..": {
                    if (!pathElements.isEmpty()) pathElements.removeLast();
                    break;
                }
                default: pathElements.add(element); break;
            }
        }
        StringBuilder result = new StringBuilder("/");
        while (!pathElements.isEmpty()) {
            result.append(pathElements.pop());
            if (pathElements.size() > 0) result.append("/");
        }
        return result.toString();
    }

    public static String simplifyPathTry3(String path) {
        Deque<String> pathElements = new ArrayDeque<>();
        for (String element : path.split("/")) {
            switch (element) {
                case "":
                case ".": break;
                case "..": if (!pathElements.isEmpty()) pathElements.pollLast();
                default: pathElements.add(element);
            }
        }
        StringBuilder result = new StringBuilder("/");
        while (!pathElements.isEmpty()) {
            result.append(pathElements.poll());
            if (pathElements.size() > 0) result.append("/");
        }
        return result.toString();
    }

    public static String simplifyPathExt(String path) {
        String[] arrayStr = path.split("/");
        System.out.println(Arrays.toString(arrayStr));

        Stack<String> result = new Stack<>();
        for (int i = 0; i < arrayStr.length; i++) {
            if (arrayStr[i].equals("..")) {
                if (!result.isEmpty()) {
                    result.pop();
                }
            } else if ((!arrayStr[i].isEmpty()) && (!arrayStr[i].equals("."))) {
                result.add(arrayStr[i]);
            }
        }
        System.out.println(result + "аааа");
        System.out.println();

        String resStr = "/";
        for (int i = 0; i < result.size(); i++) {
            resStr += result.get(i) + "/";
            System.out.println(resStr + "-----");
        }
        return resStr;
    }

    public static String simplifyPathTry4(String path) {
        Deque<String> pathElements = new ArrayDeque<>();
        for (String element : path.split("/")) {
            if (element.equals("..")) {
                if (!pathElements.isEmpty()) pathElements.pollLast();
            }
            else if (!element.equals("") && !element.equals(".")) pathElements.add(element);
        }
        StringBuilder result = new StringBuilder("/");
        while (!pathElements.isEmpty()) {
            result.append(pathElements.poll());
            if (pathElements.size() > 0) result.append("/");
        }
        return result.toString();
    }
}

