package org.example.config;

public class ParallelExecutionConfig {


    public static final int THREAD_COUNT = 4;
    public static final int FORK_COUNT = 1;
    public static final String PARALLEL_MODE = "methods";
    public static final long THREAD_TIMEOUT = 300000; // 5 minutes


    public enum Browser {
        CHROME("chrome"),
        FIREFOX("firefox"),
        EDGE("edge");

        private String name;

        Browser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public enum ThreadGroup {
        GROUP_1("Group_1"),
        GROUP_2("Group_2"),
        GROUP_3("Group_3"),
        GROUP_4("Group_4");

        private String groupName;

        ThreadGroup(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }


    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static ThreadGroup getThreadGroup() {
        long threadId = getCurrentThreadId();
        int threadIndex = (int) (threadId % THREAD_COUNT);
        return ThreadGroup.values()[threadIndex];
    }

    public static Browser getBrowserForThread() {
        long threadId = getCurrentThreadId();
        int browserIndex = (int) (threadId % Browser.values().length);
        return Browser.values()[browserIndex];
    }



    public static String getProperty(String property, String defaultValue) {
        return System.getProperty(property, defaultValue);
    }


    public static String getBrowserFromProperty() {
        return getProperty("browser", "chrome");
    }

    public static int getThreadCountFromProperty() {
        try {
            return Integer.parseInt(getProperty("threadCount", String.valueOf(THREAD_COUNT)));
        } catch (NumberFormatException e) {
            return THREAD_COUNT;
        }
    }
}

